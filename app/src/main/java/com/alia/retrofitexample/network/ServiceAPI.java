package com.alia.retrofitexample.network;

import com.alia.retrofitexample.model.PostModel;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ServiceAPI {
    @GET("/api/get")
    Observable<List<PostModel>> getData(@Query("name") String resourceName, @Query("num") int count);
}
