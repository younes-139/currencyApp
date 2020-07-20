package com.example.currencyapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("latest")
    Call<Currency> getBaseCurrencies();
    @GET("latest")
    Call<Currency> getRatesByBase(@Query("base") String base);
    @GET("history")
    Call<History> getRatesHistory(@Query("start_at") String start_at,@Query("end_at") String end_at,@Query("symbols") String symbols,@Query("base") String base);
}