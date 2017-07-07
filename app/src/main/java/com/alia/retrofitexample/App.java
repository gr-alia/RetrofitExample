package com.alia.retrofitexample;


import android.app.Application;

import com.alia.retrofitexample.network.RetrofitService;

public class App extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        RetrofitService.initInstance();
    }

}
