package com.example.currencyapp;

import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyPresenter {
    CurrencyView view;

    public CurrencyPresenter(CurrencyView view) {
        this.view = view;
    }

    public void setSpinnerCurrencies(){
        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<Currency> currencyCall = apiInterface.getBaseCurrencies();
        currencyCall.enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Currency currency = response.body();
                Map<String,Double> map = currency.getRates();
                List<String> baseCurrencies = new ArrayList<>();
                for (String i : map.keySet()) {
                    baseCurrencies.add(i);
                }
                view.onSetSpinnerData(baseCurrencies);
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {

            }
        });
    }

    public void getRatesByBase(String base){
        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<Currency> currencyCall = apiInterface.getRatesByBase(base);
        currencyCall.enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                List<Rate> rateList = new ArrayList<Rate>();
                Currency currency = response.body();
                Map<String,Double> map = currency.getRates();
                for (String i : map.keySet()) {
                    Rate rate = new Rate(i,map.get(i));
                    rateList.add(rate);
                }
                view.onSetRecyclerViewData(rateList,base);
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {

            }
        });
    }
}
