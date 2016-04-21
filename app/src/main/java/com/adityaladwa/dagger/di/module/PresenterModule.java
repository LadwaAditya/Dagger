package com.adityaladwa.dagger.di.module;

import com.adityaladwa.dagger.MainScreenContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Aditya on 21-Apr-16.
 */
@Module
public class PresenterModule {
    private final MainScreenContract.View mView;


    public PresenterModule(MainScreenContract.View mView) {
        this.mView = mView;
    }

    @Provides
    MainScreenContract.View providesMainScreenContractView() {
        return mView;
    }
}
