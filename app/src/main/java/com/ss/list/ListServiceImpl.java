package com.ss.list;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Pavan.VijayaNar on 17-Jan-16.
 */
public class ListServiceImpl implements ListServiceApi {
    String URL = "https://gist.githubusercontent.com/maclir/f715d78b49c3b4b3b77f/raw/8854ab2fe4cbe2a5919cea97d71b714ae5a4838d/items.json";
    private Call call;

    @Override
    public void getList(final ListServiceCallback callback) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(URL)
                .build();
        // Get a handler that can be used to post to the main thread
        call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                callback.onLoadFailure();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // Read data on the worker thread
                final String responseData = response.body().string();
                if (!TextUtils.isEmpty(responseData)) {
                    try {
                        JSONArray jsonArray = new JSONArray(responseData);
                        int length = jsonArray.length();
                        if (length > 0) {
                            List<Item> items = new ArrayList<>(length);
                            for (int i = 0; i < length; i++) {
                                JSONObject jsonItem = jsonArray.getJSONObject(i);
                                items.add(new Item(jsonItem.getString("title"), jsonItem.getString("description"), jsonItem.getString("image")));
                            }
                            callback.onLoadSuccess(items);
                        } else {
                            callback.onLoadFailure();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        callback.onLoadFailure();
                    }
                } else {
                    callback.onLoadFailure();
                }
            }
        });
    }

    @Override
    public void cancel() {
        call.cancel();
    }
}
