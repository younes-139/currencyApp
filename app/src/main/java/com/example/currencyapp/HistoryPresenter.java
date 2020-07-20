package com.example.currencyapp;

import android.util.Log;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryPresenter {
    HistoryView view;

    public HistoryPresenter(HistoryView view) {
        this.view = view;
    }

    public void getHistoryData(String start_at, String end_at, String symbols, String base){
        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<History> historyCall = apiInterface.getRatesHistory(start_at,end_at,symbols,base);
        historyCall.enqueue(new Callback<History>() {
            @Override
            public void onResponse(Call<History> call, Response<History> response) {
                History history = response.body();
                Map<String,Map<String,Double>> map = history.getRates();
                Map<String,Double> data = new HashMap<>();;
                for (String i : map.keySet()) {
                    Map<String,Double> map1 = map.get(i);
                    for(String j: map1.keySet()){
                        data.put(i,map1.get(j));
                    }
                }
                view.drawChat(data);
            }

            @Override
            public void onFailure(Call<History> call, Throwable t) {

            }
        });
    }
}
