package com.study.gaijuui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DataActivity extends AppCompatActivity implements View.OnClickListener,OnChartValueSelectedListener {
    //メンバ変数の宣言
    private BootstrapButton mBut_TopFromData;
    private BarChart mBarChart;
    int month;
    String strUsrfg;
    String barGraphData,pictData,pictTmp;
    boolean tgsw_flagnotice;
    //String taskText;
    private Button mtoPict;
    int num[]=new int[12];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //レイアウトファイルを貼付
        setContentView(R.layout.dataactivity);
        //インテントを発行
        Intent intent=getIntent();
        //遷移元からのパラメータを取得
        strUsrfg=intent.getStringExtra("strUsrfg");
        tgsw_flagnotice=intent.getBooleanExtra("tgsw_flagnotice",true);
        barGraphData=intent.getStringExtra("barGraphData");
        System.out.println(barGraphData);
        pictTmp=intent.getStringExtra("pictTmp");
        //String barGraphDataArray[];
        //グラフデータを配列化
        String[] barGraphDataArray=barGraphData.split(",",0);
        //グラフパラメータとして格納
        for(int i=0;i<barGraphDataArray.length;i++) {
            num[i] = Integer.parseInt(barGraphDataArray[i]);
        }

        /*Toast toast = Toast.makeText(DataActivity.this,barGraphData,Toast.LENGTH_LONG);
        toast.show();*/
        /*for(int i=0;i<12;i++){
            num[i]=i+1;
        }*/

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
        //mBarChart.setOnLongClickListener((View.OnLongClickListener) this);
        //グラフの初期設定
        createChart();
        //グラフデータをセット
        mBarChart.setData(setChartData());
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBut_TopFromData)) {//トップページへ戻る際
            Intent intent = new Intent(this, GaijuActivity.class);//トップページへのインテントの生成
            //遷移先へ持ち込むパラメータを指定
            intent.putExtra("strUsr",strUsrfg);
            intent.putExtra("tgsw_flagnotice",tgsw_flagnotice);
            startActivity(intent);//画面遷移
        }
        else if(v.equals(mtoPict)){//月別詳細表示画面へ
            //http通信用クラスのインスタンスを生成
            HttpGetTask task = new HttpGetTask();
            //URLを指定
            try {
                task.setURL(new URL("http://40.74.135.223:8080/test/mainServlet?from=0&requestID=2&Username=test"+"&month="+month));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            //http通信スレッドを立てる
            Thread thread = new Thread(task);
            //立てたスレッドを起動
            thread.start();
            try {
                //http通信が完了するまでメインスレッドを停止する
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(taskText);
            //task.getTaskText(pictData);
            //月別写真表示用文字列の取得
            pictData=task.getTaskText();
            System.out.println(pictData);
            //画面遷移先を設定
            Intent intent = new Intent(this,Activity_GaijuPict.class);
            //遷移先へ持ち込む値を設定
            intent.putExtra("month",month);
            intent.putExtra("strUsrtp",strUsrfg);
            intent.putExtra("tgsw_flagnotice",tgsw_flagnotice);
            //intent.putExtra("num",num);
            intent.putExtra("barGraphData",barGraphData);
            intent.putExtra("pictData",pictData);
            //画面遷移実行
            startActivity(intent);
        }
    }

    void createChart() {
        //グラフへのコメント
        mBarChart.setDescription("");
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
        mBarChart.getLegend().setEnabled(false);

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

        //value
        ArrayList<BarEntry> values = new ArrayList<>();
        for(int i=0;i<12;i++) {
            values.add(new BarEntry(num[i],i));//BarEntry(Val,Pos)
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
    //棒グラフをタップした際のコールバック関数
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        //月を取得
        month=e.getXIndex()+1;
        //ダイアログの生成
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(month+"月ごとの害獣写真の一覧を見ますか?");
        alertDialog.setPositiveButton("はい", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intent_exe(mtoPict);//画面遷移を実行
            }
        });
        alertDialog.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //何もしない
            }
        });
        alertDialog.create().show();
        //Toast.makeText(DataActivity.this, month+"をタップ", Toast.LENGTH_SHORT).show();
    }

    @Override
    //デフォルトでは何も動作させない
    public void onNothingSelected() {

    }

    void intent_exe(View view){
        onClick(view);
    }//onclick関数を実行する

    /*static String InputStreamToString(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    static String htmlTagRemover(String str){
        return str.replaceAll("<.+?>","");
    }

    static String brReplacer(String str){
        return str.replaceAll("<br.+?",",");
    }

    //http通信用クラス
    public class HttpGetTask implements Runnable {

        private URL url;

        public void setURL(URL url1) {
            url = url1;
        }

        @Override
        public void run() {
            final StringBuilder result = new StringBuilder();
            try {
                //URL url = new URL("http://www.drk7.jp/weather/xml/27.xml");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                taskText = InputStreamToString(con.getInputStream());
                //Log.d("HTTP", taskText);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }*/
}