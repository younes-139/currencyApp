package com.example.currencyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity2 extends AppCompatActivity implements HistoryView{

    @BindView(R.id.baseCurrency)
    TextView baseCurrency;
    @BindView(R.id.targetCurrency)
    TextView targetCurrency;
    @BindView(R.id.chart) LineChart lineChart;
    HistoryPresenter historyPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        String base = getIntent().getStringExtra("baseCurrency");
        String target = getIntent().getStringExtra("targetCurrency");
        baseCurrency.setText("1 "+base);
        targetCurrency.setText(target);

        historyPresenter = new HistoryPresenter(this);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        //end_date
        Date current_date = Calendar.getInstance().getTime();
        String end_at = df.format(current_date);
        //start_date
        Calendar calendar=Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -60);
        Date start_date = calendar.getTime();
        String start_at = df.format(start_date);

        historyPresenter.getHistoryData(start_at,end_at,target,base);
    }

    @Override
    public void drawChat(Map<String,Double> data) {
        List<Entry> ratesEntries = new ArrayList<>();
        List<String> xAxisValues = new ArrayList<>();
        int i=0;
        for (String date: data.keySet()){
            xAxisValues.add(date);
            ratesEntries.add(new Entry(i, data.get(date).floatValue()));
            i++;
        }
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        LineDataSet set1;
        set1 = new LineDataSet(ratesEntries, "Rates");
        set1.setColor(Color.rgb(58, 61, 60));
        set1.setValueTextColor(Color.rgb(55, 70, 73));
        set1.setDrawCircles(false);
        set1.setDrawValues(false);
        set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSets.add(set1);

        //to hide right Y and top X border
        YAxis rightYAxis = lineChart.getAxisRight();
        rightYAxis.setEnabled(false);

        XAxis topXAxis = lineChart.getXAxis();
        topXAxis.setEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setEnabled(true);
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        lineChart.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xAxisValues));

        LineData chartData = new LineData(dataSets);
        lineChart.setData(chartData);
        lineChart.animateX(2000);
        lineChart.invalidate();
        lineChart.getLegend().setEnabled(false);
        lineChart.getDescription().setEnabled(false);

    }
}