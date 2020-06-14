package com.gne.test.retrofit;

import com.gne.test.vo.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

//    https://randomuser.me/api/?results=10
    @GET("/api/")
    Call<Result> getUsers(@Query("results") int count);
}
