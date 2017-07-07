package com.alia.retrofitexample.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arhis on 07.07.2017.
 */

public class RetrofitService {

    private static RetrofitService instance;

    private ServiceAPI serviceImpl;

    public static void initInstance() {
        instance = new RetrofitService();
    }

    public static RetrofitService getInstance() {
        return instance;
    }

    private RetrofitService() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://umorili.herokuapp.com") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create(gson))//Конвертер, необходимый для преобразования JSON'а в объекты
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build();
        serviceImpl = retrofit.create(ServiceAPI.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public ServiceAPI getApi() {
        return serviceImpl;
    }
}
