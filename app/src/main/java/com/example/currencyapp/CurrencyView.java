package com.example.currencyapp;

import java.util.List;

public interface CurrencyView {
    void onSetSpinnerData(List<String> baseCurrencies);
    void onSetRecyclerViewData(List<Rate> rates,String base);
}
