package com.ss.list;

import com.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LoginModule.class, ListServiceModule.class, ListPresenterModule.class})
interface MainComponent {

    void inject(MainActivity activity);
}