package com.akshay.fooddelivery;

import android.app.Application;

/**
 * Created by Akshay on 20-02-2018.
 */

public class MyApplication extends Application{

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }
}
