package net.dixq.caloriemanager;

import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * Created by dixq on 2018/03/25.
 */

public class Graph implements View.OnClickListener {

    Graph(LineChart lineChart){
        ArrayList<LineDataSet> dataSets = new ArrayList<>();

        // X軸の値
        ArrayList<String> xValues = new ArrayList<>();
        xValues.add("");
        xValues.add("");
        xValues.add("");
        xValues.add("");

        // value
        ArrayList<Entry> value = new ArrayList<>();
        value.add(new Entry(50.2f, 0));
        value.add(new Entry(48.9f, 1));
        value.add(new Entry(51.2f, 2));
        value.add(new Entry(50.8f, 3));

        LineDataSet valueDataSet = new LineDataSet(value, "sample");
        dataSets.add(valueDataSet);

        lineChart.animateY(1000);

        lineChart.setData(new LineData(xValues, dataSets));

        lineChart.getAxisLeft().setStartAtZero(false);
        lineChart.getAxisLeft().setAxisMinValue(48);
        lineChart.getAxisLeft().setAxisMaxValue(52);

        lineChart.getLegend().setEnabled(false);

        lineChart.getAxisRight().setEnabled(false);

        lineChart.setOnClickListener(this);

        lineChart.setDescription("");
    }

    @Override
    public void onClick(View view) {

    }
}
