package com.study.gaijuui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.Inflater;

public class Activity_GaijuPict extends AppCompatActivity implements View.OnClickListener {
    //private LinearLayout Layout;
    private BootstrapButton mtoBarData;
    int month;
    String strUsrtp;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaiju_pict);
        /*final int WC=ViewGroup.LayoutParams.WRAP_CONTENT;
        final int MP=ViewGroup.LayoutParams.MATCH_PARENT;*/
        Intent intent=getIntent();
        month=intent.getIntExtra("month",0);
        strUsrtp=intent.getStringExtra("strUsrtp");
        /*LinearLayout Layout=new LinearLayout(this);
        Layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(Layout);
        TextView textView=new TextView(this);
        textView.setText("Test TextView");
        Layout.addView(textView,new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT
        ));*/
        /*Layout=(LinearLayout)findViewById(R.id.rootpict);
        Layout.setOrientation(LinearLayout.VERTICAL);
        Layout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
        setContentView(Layout);
        mtoBarData.setBootstrapText("グラフ画面へ戻る {fa-long-arrow-left}");
        mtoBarData.setOnClickListener(this);*/
        /*ImageView iv = new ImageView(getApplicationContext());
        iv.setLayoutParams(new LinearLayout.LayoutParams(MP,WC));

        URL url;
        InputStream istream;

        try{
            url=new URL("https://www.gstatic.com/android/market_images/web/play_logo_x2.png");
            istream=url.openStream();
            Bitmap bmp=BitmapFactory.decodeStream(istream);
            iv.setImageBitmap(bmp);
            istream.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        Layout.addView(iv);*/
        //以下静的な画像の表示
        ImageView img1=(ImageView)findViewById(R.id.picttest1);
        try {
            InputStream istream = getAssets().open("shishitest1.png");
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            img1.setImageBitmap(bitmap);
        } catch (IOException e) {//例外(エラー)処理
            Log.d("Asetts", "Error");
        }

        ImageView img2=(ImageView)findViewById(R.id.picttest2);
        try {
            InputStream istream = getAssets().open("shishitest2.png");
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            img2.setImageBitmap(bitmap);
        } catch (IOException e) {//例外(エラー)処理
            Log.d("Asetts", "Error");
        }

        ImageView img3=(ImageView)findViewById(R.id.picttest3);
        try {
            InputStream istream = getAssets().open("shishitest3.png");
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            img3.setImageBitmap(bitmap);
        } catch (IOException e) {//例外(エラー)処理
            Log.d("Asetts", "Error");
        }
        mtoBarData=(BootstrapButton)findViewById(R.id.toBarData);
        mtoBarData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mtoBarData)){
            Intent intent=new Intent(this,DataActivity.class);
            intent.putExtra("month",month);
            intent.putExtra("strUsrfg",strUsrtp);
            startActivity(intent);
        }
    }
}
