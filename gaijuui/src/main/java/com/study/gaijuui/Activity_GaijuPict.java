package com.study.gaijuui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapText;
import com.beardedhen.androidbootstrap.api.attributes.BootstrapBrand;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapBrand;
import com.beardedhen.androidbootstrap.api.defaults.DefaultBootstrapSize;
import com.beardedhen.androidbootstrap.font.FontAwesome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.Inflater;

import static android.R.attr.left;
import static com.beardedhen.androidbootstrap.api.attributes.BootstrapBrand.*;

public class Activity_GaijuPict extends AppCompatActivity implements View.OnClickListener {
    //private LinearLayout Layout;
    private BootstrapButton mtoBarData;
    int month,buttonNum;
    int imageWidth=500,imageHeight=500,layoutWidth=500,layoutHeight=500;
    int num[]=new int[12];
    String strUsrtp,barGraphData,pictData,taskText,pictTmp;
    String str;
    final int WC=ViewGroup.LayoutParams.WRAP_CONTENT;
    final int MP=ViewGroup.LayoutParams.MATCH_PARENT;
    boolean tgsw_flagnotice;
    ScrollView scrollView;
    LinearLayout layout_root,layout_pict;
    TextView tv;
    ImageView img;
    LinearLayout.LayoutParams layoutPictParams;
    Uri uri;
    Button[] btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_gaiju_pict);
        //インテントを取得
        Intent intent=getIntent();
        //遷移元からのパラメータを取得
        month=intent.getIntExtra("month",0);
        strUsrtp=intent.getStringExtra("strUsrtp");
        barGraphData=intent.getStringExtra("barGraphData");
        pictData=intent.getStringExtra("pictData");
        pictTmp=intent.getStringExtra("pictTmp");
        tgsw_flagnotice=intent.getBooleanExtra("tgsw_flagnotice",true);
        System.out.println(pictData);
        //写真データを配列化
        String[] pictDataArray=pictData.split(",",0);
        /*String[] pictDateData=new String[pictDataArray.length];
        String[] pictUrlData=new String[pictDataArray.length];
        for(int i=0;i<pictDataArray.length;i++)
            System.out.println(pictDataArray[i]);
        for(int i=0;i<pictDataArray.length;i+=2){
            int count=0;
            pictDateData[count++]=pictDataArray[i];
            System.out.println(pictDateData[i]);
        }
        for(int i=1;i<pictDataArray.length;i+=2){
            int count=0;
            pictUrlData[count++]=pictDataArray[i];
            System.out.println(pictUrlData[i]);
        }*/


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
        //以下静的な画像の表示
        //ImageView img1=(ImageView)findViewById(R.id.picttest1);
        //LinearLayout.LayoutParams layoutPictParams=new LinearLayout.LayoutParams(imageWidth,imageHeight);
        //ImageView img1 =new ImageView(this);
        //Uri uri=Uri.parse("http://cdn-ak.f.st-hatena.com/images/fotolife/f/fjswkun/20150927/20150927140905.jpg");

        //uri=Uri.parse("http://40.74.135.223/pic/3/3/1472012650971.jpg");
        /*createLayoutPict();
        initPict();
        uri=Uri.parse("http://inoshishi.etc64.com/image/inoshishi04.jp");
        pictSet(uri,img,layoutPictParams,layout_pict);*/

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
        //レイアウトの初期設定
        initLayout();
        //生成するボタンの数を取得
        buttonNum=pictDataArray.length/2;
        //その月の写真の数が0枚でなければ
        if(buttonNum!=0) {
            //ボタンを生成しコールバック用リスナに登録する
            btn = new Button[buttonNum];
            for (int i = 0; i < buttonNum; i++) {
                btn[i] = new Button(this);
                btn[i].setOnClickListener(this);
            }
            for (int i = 0, j = 0; i < pictDataArray.length; i++, j++) {
                //写真とボタンを表示するリニアレイアウトを生成
                createLayoutPict();
                //写真表示用の初期設定
                initPict();
                //uri=Uri.parse("http://inoshishi.etc64.com/image/inoshishi04.jpg");
                //uriを設定
                uri = Uri.parse(pictDataArray[i + 1]);
                //ボタンを表示
                setText(pictDataArray[i++], btn[j], layout_pict);
                //写真を表示
                pictSet(uri, img, layoutPictParams, layout_pict);
            }
        }
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
        /*ImageView img3=new ImageView(this);
        try {
            InputStream istream = getAssets().open("shishitest3.png");
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            img3.setImageBitmap(bitmap);
        } catch (IOException e) {//例外(エラー)処理
            Log.d("Asetts", "Error");
        }
        img3.setScaleType(ImageView.ScaleType.FIT_CENTER);
        layout.addView(img3);*/
        /*buttonWidth=500;
        buttonHeight=100;
        LinearLayout.LayoutParams layoutButtonParams=new LinearLayout.LayoutParams(500,500);*/
        //メンバ変数をウィジェットにキャスト
        mtoBarData=(BootstrapButton)findViewById(R.id.toBarData);
        //棒グラフ画面へと戻るボタンの設定
        mtoBarData=new BootstrapButton(this);
        mtoBarData.setBootstrapSize(DefaultBootstrapSize.XL);
        mtoBarData.setBootstrapBrand(DefaultBootstrapBrand.INFO);
        BootstrapText.Builder bst=new BootstrapText.Builder(this,true);
        BootstrapText bootstrapText=bst.addFontAwesomeIcon("fa-long-arrow-left")
                .addText(" グラフ画面へ戻る").build();
        mtoBarData.setBootstrapText(bootstrapText);
        mtoBarData.setOnClickListener(this);
        mtoBarData.setLayoutParams(new LinearLayout.LayoutParams(WC,WC));
        mtoBarData.setGravity(Gravity.BOTTOM);
        //画面へadd
        layout_root.addView(mtoBarData);
        //スクロール画面へadd
        scrollView.addView(layout_root);
        //レイアウトとしてスクロールビューをセット
        setContentView(scrollView);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mtoBarData)){//棒グラフ画面へ戻るボタンが押されたら
            //インテントを発行
            HttpGetTask task =new HttpGetTask();
            try {
                task.setURL(new URL("http://40.74.135.223:8080/test/mainServlet?from=0&requestID=1&Username=test"));
            }catch(MalformedURLException e){
                e.printStackTrace();
            }
            Thread thread = new Thread(task);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            barGraphData=task.getTaskText();
            Intent intent=new Intent(this,DataActivity.class);
            //遷移先へ持ち込むパラメータの指定
            intent.putExtra("month",month);
            intent.putExtra("strUsrfg",strUsrtp);
            intent.putExtra("barGraphData",barGraphData);
            intent.putExtra("pictTmp",pictTmp);
            intent.putExtra("tgsw_flagnotice",tgsw_flagnotice);
            //遷移を実行
            startActivity(intent);
        }
        for(int i=0;i<buttonNum;i++){
            if(v.equals(btn[i])) {//一件削除ボタンが押されたら
                //ボタンに表示されている日時を取得
                String dateTime=btn[i].getText().toString();
                //日付と時刻に分ける
                final String[] dateTimeArray=dateTime.split("[\\s]+",0);
                System.out.println(dateTimeArray[0]);
                System.out.println(dateTimeArray[1]);
                //ダイアログの生成
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Activity_GaijuPict.this);
                alertDialog.setTitle("注意!!");
                alertDialog.setMessage(dateTimeArray[1]+"の写真を消してもいいですか？");
                alertDialog.setPositiveButton("消去する", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //http通信用クラスのインスタンスを生成
                        final HttpGetTask task = new HttpGetTask();
                        try {
                            task.setURL(new URL("http://40.74.135.223:8080/test/mainServlet?from=0&requestID=3&Username=test"+"&Date="+dateTimeArray[0]+"&Time="+dateTimeArray[1]));
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
                        System.out.println(taskText);
                        //削除に成功したら
                        if(task.getTaskText().equals("true,"))
                            Toast.makeText(Activity_GaijuPict.this,"消去に成功しました",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(Activity_GaijuPict.this,"消去に失敗しました",Toast.LENGTH_SHORT).show();
                    }
                });
                //キャンセルを押したら
                alertDialog.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Activity_GaijuPict.this,"キャンセルしました",Toast.LENGTH_SHORT).show();
                    }
                });
                //task.getTaskText(pictData);
                //Toast.makeText(Activity_GaijuPict.this, btn[i].getText().toString(), Toast.LENGTH_SHORT).show();
                alertDialog.create().show();
            }
        }
    }

    //以下レイアウト用メソッド
    public void initLayout(){
        scrollView=new ScrollView(this);
        layout_root=new LinearLayout(this);
        layout_root.setOrientation(layout_root.VERTICAL);
        layout_root.setLayoutParams(new LinearLayout.LayoutParams(MP,MP));
        layout_root.setGravity(Gravity.CENTER);
        layout_root.setBackgroundColor(Color.rgb(240,230,140));
    }

    public void createLayoutPict(){
        layout_pict=new LinearLayout(this);
        layout_pict.setOrientation(layout_pict.HORIZONTAL);
        layout_pict.setLayoutParams(new LinearLayout.LayoutParams(MP,MP));
    }

    public void initPict(){
        layoutPictParams=new LinearLayout.LayoutParams(imageWidth,imageHeight);
        img =new ImageView(this);
    }

    public void setText(String message,Button btn,LinearLayout layout){
        //tv=new TextView(this);
        btn.setText(message);
        //tv.setTextColor(Color.BLACK);
        LinearLayout.LayoutParams textGravity=new LinearLayout.LayoutParams(WC,WC);
        textGravity.gravity=Gravity.BOTTOM;
        //textGravity.gravity=Gravity.LEFT;
        btn.setLayoutParams(textGravity);
        layout.addView(btn);
        //layout_root.addView(layout_pict);
    }
    public void pictSet(Uri uri,ImageView iv,LinearLayout.LayoutParams pictSize,LinearLayout layout){
        Uri.Builder builder=uri.buildUpon();
        HttpGetPict pict=new HttpGetPict(iv);
        pict.execute(builder);
        iv.setLayoutParams(pictSize);
        layout.addView(iv);
        layout_root.addView(layout);
    }

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
