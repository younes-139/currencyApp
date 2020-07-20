package com.example.currencyapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.exchangeratesapi.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
