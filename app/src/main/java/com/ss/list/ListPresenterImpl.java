package com.ss.list;

import java.util.List;

/**
 * Created by Pavan.VijayaNar on 17-Jan-16.
 */
public class ListPresenterImpl implements ListPresenter, ListServiceApi.ListServiceCallback{

    private final MainView mView;
    private final ListServiceApi mService;

    public ListPresenterImpl(MainView viewIntf) {
        mView = viewIntf;
        mService = new ListServiceImpl();
    }

    @Override
    public void fetchList() {
        mView.showProgress();
        // Initiate Request for items
        mService.getList(this);
    }

    @Override
    public void onStop() {
        mService.cancel();
    }


    @Override
    public void onLoadFailure() {
        mView.hideProgress();
        mView.setErrorMessage("Loading Failed");
    }

    @Override
    public void onLoadSuccess(List<Item> itemsList) {
        mView.hideProgress();
        if (itemsList == null || itemsList.isEmpty()) {
            mView.setErrorMessage("No Results");
        }
        else {
            mView.setItems(itemsList);
        }
    }
}
