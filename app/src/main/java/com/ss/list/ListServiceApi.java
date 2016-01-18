package com.ss.list;

import java.util.List;

/**
 * Created by Pavan.VijayaNar on 17-Jan-16.
 */
public interface ListServiceApi {

    interface ListServiceCallback {
        void onLoadFailure();
        void onLoadSuccess(List<Item> itemsList);
    }

    void getList(ListServiceCallback callback);

    void cancel();
}