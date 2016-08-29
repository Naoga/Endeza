package com.study.gaijuui;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapText;
import com.beardedhen.androidbootstrap.api.attributes.BootstrapBrand;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapSize;
import com.beardedhen.androidbootstrap.font.FontAwesome;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.Inflater;

import static android.R.attr.left;
import static com.beardedhen.androidbootstrap.api.attributes.BootstrapBrand.*;

public class Activity_GaijuPict extends AppCompatActivity implements View.OnClickListener {
    //private LinearLayout Layout;
    private BootstrapButton mtoBarData;
    int month;
    int imageWidth=500,imageHeight=500,buttonWidth,buttonHeight;
    String strUsrtp;
    String str;
    final int WC=ViewGroup.LayoutParams.WRAP_CONTENT;
    final int MP=ViewGroup.LayoutParams.MATCH_PARENT;
    ScrollView scrollView;
    LinearLayout layout;
    ImageView img;
    LinearLayout.LayoutParams layoutPictParams;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_gaiju_pict);
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

        /*ScrollView scrollView=new ScrollView(this);
        LinearLayout layout=new LinearLayout(this);*/
        initLayout();
        initPict();
        //以下静的な画像の表示
        //ImageView img1=(ImageView)findViewById(R.id.picttest1);
        //LinearLayout.LayoutParams layoutPictParams=new LinearLayout.LayoutParams(imageWidth,imageHeight);
        //ImageView img1 =new ImageView(this);
        //Uri uri=Uri.parse("http://cdn-ak.f.st-hatena.com/images/fotolife/f/fjswkun/20150927/20150927140905.jpg");

        uri=Uri.parse("http://40.74.135.223/pic/3/3/1472012650971.jpg");
        pictSet(uri,img,layoutPictParams,layout);

        /*Uri.Builder builder=uri.buildUpon();
        HttpGetPict pict=new HttpGetPict(img1);
        pict.execute(builder);
        img1.setLayoutParams(layoutPictParams);
        layout.addView(img1);*/

        /*try {
            InputStream istream = getAssets().open("shishitest1.png");
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            img1.setImageBitmap(bitmap);
        } catch (IOException e) {//例外(エラー)処理
            Log.d("Asetts", "Error");
        }*/

        initPict();
        uri=Uri.parse("http://inoshishi.etc64.com/image/inoshishi04.jpg");
        pictSet(uri,img,layoutPictParams,layout);

        //ImageView img2=(ImageView)findViewById(R.id.picttest2);
        /*ImageView img2=new ImageView(this);
        try {
            InputStream istream = getAssets().open("shishitest2.png");
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            img2.setImageBitmap(bitmap);
        } catch (IOException e) {//例外(エラー)処理
            Log.d("Asetts", "Error");
        }
        img2.setScaleType(ImageView.ScaleType.FIT_CENTER);
        layout.addView(img2);*/

        //ImageView img3=(ImageView)findViewById(R.id.picttest3);
        ImageView img3=new ImageView(this);
        try {
            InputStream istream = getAssets().open("shishitest3.png");
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            img3.setImageBitmap(bitmap);
        } catch (IOException e) {//例外(エラー)処理
            Log.d("Asetts", "Error");
        }
        img3.setScaleType(ImageView.ScaleType.FIT_CENTER);
        layout.addView(img3);
        /*buttonWidth=500;
        buttonHeight=100;
        LinearLayout.LayoutParams layoutButtonParams=new LinearLayout.LayoutParams(500,500);*/
        mtoBarData=(BootstrapButton)findViewById(R.id.toBarData);
        mtoBarData=new BootstrapButton(this);
        mtoBarData.setBootstrapSize(DefaultBootstrapSize.XL);
        mtoBarData.setBootstrapBrand(DefaultBootstrapBrand.INFO);
        BootstrapText.Builder bst=new BootstrapText.Builder(this,true);
        BootstrapText bootstrapText=bst.addFontAwesomeIcon("fa-long-arrow-left")
                .addText(" グラフ画面へ戻る").build();
        mtoBarData.setBootstrapText(bootstrapText);
        mtoBarData.setOnClickListener(this);
        mtoBarData.setLayoutParams(new LinearLayout.LayoutParams(WC,WC));
        layout.addView(mtoBarData);

        scrollView.addView(layout);

        setContentView(scrollView);
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

    public void initLayout(){
        scrollView=new ScrollView(this);
        layout=new LinearLayout(this);
        layout.setOrientation(layout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(MP,MP));
        layout.setGravity(Gravity.CENTER);
    }

    public void initPict(){
        layoutPictParams=new LinearLayout.LayoutParams(imageWidth,imageHeight);
        img =new ImageView(this);
    }

    public void pictSet(Uri uri,ImageView iv,LinearLayout.LayoutParams pictSize,LinearLayout layout){
        Uri.Builder builder=uri.buildUpon();
        HttpGetPict pict=new HttpGetPict(iv);
        pict.execute(builder);
        iv.setLayoutParams(pictSize);
        layout.addView(iv);
    }


}
