package com.study.gaijuui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity implements View.OnClickListener {

    private BootstrapButton mBut_TopFromData;
    private BarChart mBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dataactivity);

        mBut_TopFromData = (BootstrapButton) findViewById(R.id.but_topfromdata);
        mBut_TopFromData.setOnClickListener(this);
        //棒グラフ用インスタンスの生成
        mBarChart = (BarChart) findViewById(R.id.bar_chart);
        createChart();
        mBarChart.setData(setChartData());
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBut_TopFromData)) {
            Intent intent = new Intent(this, GaijuActivity.class);
            startActivity(intent);
        }
    }

    void createChart() {
        //グラフへのコメント
        mBarChart.setDescription("ここに説明が表示される");
        //グラフの縦軸設定(左側のみ表示)
        mBarChart.getAxisRight().setEnabled(false);
        mBarChart.getAxisLeft().setEnabled(true);
        //グリッド
        mBarChart.setDrawGridBackground(true);
        mBarChart.setDrawBarShadow(false);
        mBarChart.setEnabled(true);
        //タッチ・ズーム系統
        mBarChart.setTouchEnabled(true);
        mBarChart.setPinchZoom(true);
        mBarChart.setDoubleTapToZoomEnabled(true);

        mBarChart.setHighlightEnabled(true);
        mBarChart.setDrawHighlightArrow(true);
        mBarChart.setDrawValueAboveBar(true);

        mBarChart.setScaleEnabled(true);
        //ラインの凡例の設定
        mBarChart.getLegend().setEnabled(true);

        //X軸の設定
        XAxis xAxis = mBarChart.getXAxis();
        xAxis.setDrawLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setSpaceBetweenLabels(0);

        //Y軸の設定
        YAxis yAxis = mBarChart.getAxisLeft();
        yAxis.setTextColor(Color.BLACK);
        yAxis.setAxisMinValue(0);
        yAxis.setAxisMaxValue(20);
        yAxis.setDrawGridLines(true);

        //データ追加時の再描画
        /*barChart.notifyDataSetChanged();
        barChart.invalidate();*/
    }

    BarData setChartData() {
        ArrayList<BarDataSet> barDataSets = new ArrayList<>();

        //x軸
        ArrayList<String> xValues = new ArrayList<>();
        for(int i=0;i<12;i++){
            //String s=(i+1)+"月";
            String s="";
            xValues.add(s);
        }

        //value(テスト用・静的な値)
        ArrayList<BarEntry> values = new ArrayList<>();
        for(int i=0;i<12;i++) {
            values.add(new BarEntry(i+1,i));
        }

        BarDataSet valuesDataSet = new BarDataSet(values,"");
        valuesDataSet.setColor(ColorTemplate.COLORFUL_COLORS[3]);

        barDataSets.add(valuesDataSet);

        BarData barData = new BarData(xValues,barDataSets);
        return barData;
    }
}