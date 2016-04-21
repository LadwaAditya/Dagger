package com.adityaladwa.dagger.di.components;

import com.adityaladwa.dagger.MainActivity;
import com.adityaladwa.dagger.di.module.AppModule;
import com.adityaladwa.dagger.di.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Aditya on 21-Apr-16.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    void inject(MainActivity activity);
}
