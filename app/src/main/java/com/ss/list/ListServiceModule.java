package com.ss.list;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Pavan.VijayaNar on 12/13/2016.
 */
@Module
public class ListServiceModule {
    @Provides @Singleton ListServiceApi provideListService() {
        return new ListServiceImpl();
    }
}
