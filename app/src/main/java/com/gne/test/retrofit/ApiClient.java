package com.gne.test.retrofit;

import com.gne.test.BuildConfig;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class ApiClient {

    private static ApiInterface apiInterface;
    private ApiClient(){}

    public static ApiInterface getInstance(){
        if(apiInterface ==null){

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .callTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build();

            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiInterface =retrofit.create(ApiInterface.class);
        }
        return apiInterface;
    }
}
