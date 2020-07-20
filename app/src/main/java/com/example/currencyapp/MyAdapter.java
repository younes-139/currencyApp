package com.example.currencyapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Rate> rateList;
    String base;
    Context context;

    public MyAdapter(List<Rate> rateList, String base, Context context) {
        this.rateList = rateList;
        this.base = base;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_item,parent,false);
        return new  MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.textCurrency.setText(rateList.get(position).getCurrencyName());
        holder.textRate.setText(String.valueOf(rateList.get(position).getCurrencyRate()));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,MainActivity2.class);
                intent.putExtra("targetCurrency",rateList.get(position).getCurrencyName());
                intent.putExtra("baseCurrency",base);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rateList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.currency)
        TextView textCurrency;
        @BindView(R.id.rate)
        TextView textRate;
        @BindView(R.id.btn)
        Button button;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
