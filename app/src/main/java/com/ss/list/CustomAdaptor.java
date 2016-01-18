package com.ss.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pavan.VijayaNar on 17-Jan-16.
 */
public class CustomAdaptor extends BaseAdapter {
    
    private final List<Item> mDataList;
    private final Context mContext;

    public CustomAdaptor(Context context, List<Item> itemsList) {
        mDataList = itemsList;
        mContext = context;
    }
    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Item getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (null == view) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.list_item, parent, false);
        }
        Item item = getItem(position);
        TextView text1 = (TextView) view.findViewById(R.id.text1);
        TextView text2 = (TextView) view.findViewById(R.id.text2);
        text1.setText(item.getTitle());
        text2.setText(item.getDescription());
        return view;
    }
}
