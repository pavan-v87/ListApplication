package com.ss.list;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Pavan.VijayaNar on 12/13/2016.
 */
@Module
public class ListPresenterModule {
    @Provides ListPresenter provideListPresenter(MainView view, ListServiceApi service) {
        return new ListPresenterImpl(view, service);
    }
}
