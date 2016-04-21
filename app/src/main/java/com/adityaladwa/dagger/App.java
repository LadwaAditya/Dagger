package com.adityaladwa.dagger;

import android.support.multidex.MultiDexApplication;

import com.adityaladwa.dagger.di.components.DaggerNetComponent;
import com.adityaladwa.dagger.di.components.NetComponent;
import com.adityaladwa.dagger.di.module.AppModule;
import com.adityaladwa.dagger.di.module.NetModule;
import com.adityaladwa.dagger.di.module.PresenterModule;

/**
 * Created by Aditya on 21-Apr-16.
 */
public class App extends MultiDexApplication {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
//        mNetComponent = DaggerNetComponent.builder()
//                .appModule(new AppModule(this))
//                .netModule(new NetModule("http://www.jsonplaceholder.typicode.com"))
//                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
