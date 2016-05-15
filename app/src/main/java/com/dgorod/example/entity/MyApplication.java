package com.dgorod.example.entity;

import android.app.Application;

import com.dgorod.example.api.ApiService;

/**
 * Created by Dmytro Gorodnytskyi on 15-May-16.
 */
public final class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ApiService.init(this);
    }
}
