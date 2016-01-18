package com.ss.list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragments extends Fragment {

    private final static String KEY_IMAGE_URL = "image-url";
    private final static String KEY_TITLE = "title";
    private final static String KEY_DESCRIPTION = "description";

    private final static String KEY_SAVED_STATE = "saved-state";

    public DetailsFragments() {
        // Required empty public constructor
    }

    public static DetailsFragments newInstance(Item item) {
        DetailsFragments fragment = new DetailsFragments();
        if (item != null) {
            Bundle detail = new Bundle();
            detail.putString(KEY_TITLE, item.getTitle());
            detail.putString(KEY_DESCRIPTION, item.getDescription());
            detail.putString(KEY_IMAGE_URL, item.getImageUrl());
            fragment.setArguments(detail);
        }
        return fragment;
    }

     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.details_fragment, container, false);
         Bundle detail = getArguments();

         if (detail == null && null != savedInstanceState) { // May be we have saved state
             detail = savedInstanceState.getBundle(KEY_SAVED_STATE);
         }

         if (null != detail) {
             TextView title = (TextView) view.findViewById(R.id.title);
             title.setText(detail.getString(KEY_TITLE));

             TextView description = (TextView) view.findViewById(R.id.description);
             description.setText(detail.getString(KEY_DESCRIPTION));

             ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
             Glide.with(this).load(detail.getString(KEY_IMAGE_URL)).into(imageView);
         }
         return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (null != getArguments()) {
            outState.putBundle(KEY_SAVED_STATE, getArguments());
        }
        super.onSaveInstanceState(outState);
    }
}
