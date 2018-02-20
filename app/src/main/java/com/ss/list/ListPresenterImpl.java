package com.ss.list;

import com.login.Login;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Pavan.VijayaNar on 17-Jan-16.
 */
public class ListPresenterImpl implements ListPresenter, ListServiceApi.ListServiceCallback{

    private final MainView mView;
    private final ListServiceApi mService;

    @Inject
    Login loginService;

    @Inject
    public ListPresenterImpl(MainView viewIntf, ListServiceApi service) {
        mView = viewIntf;
        mService = service;
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
