package com.ss.list;

import java.util.List;

/**
 * Created by Pavan.VijayaNar on 17-Jan-16.
 */
public interface MainView {

    void showProgress();

    void hideProgress();

    void setErrorMessage(String msg);

    void setItems(List<Item> items);
}
