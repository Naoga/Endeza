package com.study.gaijuui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity implements View.OnClickListener,OnChartValueSelectedListener {
    //メンバ変数の宣言
    private BootstrapButton mBut_TopFromData;
    private BarChart mBarChart;
    int month;
    String strUsrfg;
    private Button mtoPict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dataactivity);
        Intent intent=getIntent();
        strUsrfg=intent.getStringExtra("strUsrfg");

        //メンバ変数をBootstrap用にキャスト
        mBut_TopFromData = (BootstrapButton) findViewById(R.id.but_topfromdata);
        mtoPict=(Button) findViewById(R.id.toPict);
        mtoPict.setVisibility(View.GONE);
        //OnClick用関数のリスナに設定
        mBut_TopFromData.setOnClickListener(this);
        mtoPict.setOnClickListener(this);
        //メンバ変数を棒グラフ用にキャスト
        mBarChart = (BarChart) findViewById(R.id.bar_chart);
        mBarChart.setOnChartValueSelectedListener(this);
        //グラフの初期設定
        createChart();
        //グラフデータをセット
        mBarChart.setData(setChartData());
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBut_TopFromData)) {//トップページへ戻る際
            Intent intent = new Intent(this, GaijuActivity.class);//トップページへのインテントの生成
            intent.putExtra("strUsr",strUsrfg);
            startActivity(intent);//画面遷移
        }
        else if(v.equals(mtoPict)){
            Intent intent = new Intent(this,Activity_GaijuPict.class);
            intent.putExtra("month",month);
            intent.putExtra("strUsrtp",strUsrfg);
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

        //x軸ラベル
        ArrayList<String> xValues = new ArrayList<>();
        for(int i=0;i<12;i++){
            String s=(i+1)+"月";
            xValues.add(s);
        }

        //value(テスト用・静的な値)
        ArrayList<BarEntry> values = new ArrayList<>();
        for(int i=0;i<12;i++) {
            values.add(new BarEntry(i+1,i));//BarEntry(Val,Pos)
        }

        //凡例の設定 "　"内に記述
        BarDataSet valuesDataSet = new BarDataSet(values,"");
        //色の設定
        valuesDataSet.setColor(ColorTemplate.COLORFUL_COLORS[3]);
        //凡例とデータをセット
        barDataSets.add(valuesDataSet);
        //X軸ラベルとデータを棒グラフ用クラスのフィールドに格納
        BarData barData = new BarData(xValues,barDataSets);

        return barData;
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        month=e.getXIndex()+1;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(month+"月ごとの害獣写真の一覧を見ますか?");
        alertDialog.setPositiveButton("はい", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent_exe(mtoPict);
            }
        });
        alertDialog.create().show();
        //Toast.makeText(DataActivity.this, month+"をタップ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected() {

    }

    void intent_exe(View view){
        onClick(view);
    }
}