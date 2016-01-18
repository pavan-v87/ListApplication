package com.ss.list;

/**
 * Created by Pavan.VijayaNar on 17-Jan-16.
 */
public class Item {
    private final String mTitle;
    private final String mDescription;
    private final String mImageUrl;

    public Item(String title, String description, String imageUrl) {
        mTitle = title;
        mDescription = description;
        mImageUrl = imageUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}
