package com.alia.retrofitexample;


import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
    private static ServiseAPI sServiseAPI;
    private Retrofit mRetrofit;

    @Override
    public void onCreate(){
        super.onCreate();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://www.umori.li") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create(gson)) //Конвертер, необходимый для преобразования JSON'а в объекты

                .build();
        sServiseAPI = mRetrofit.create(ServiseAPI.class); //Создаем объект, при помощи которого будем выполнять запросы
    }
    public static ServiseAPI getApi(){
        return sServiseAPI;
    }
}
