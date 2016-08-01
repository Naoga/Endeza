package com.study.gaijuui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.WindowDecorActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Application;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GaijuActivity extends Activity implements View.OnClickListener{
    //widgetを格納するメンバ変数の宣言
    private BootstrapButton mBut_Data;
    private BootstrapButton mBut_Conf;
    private TextView mUsrtxt;
    //private EditText mTestText ;
    //OnCreate()内に動作を記述する

    //トグルボタンの状態を保持するフィールド
    boolean tgsw_flagnotice;
    boolean tgsw_flagsave;
    //ユーザ名の初期値はnull
    String userName=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaiju);
        //遷移元のインテントを取得
        Intent intent=getIntent();
        //通知用トグルボタンの状態を取得
        tgsw_flagnotice=intent.getBooleanExtra("tgsw_flagnotice",true);
        //省電力用トグルボタンの状態を取得
        tgsw_flagsave=intent.getBooleanExtra("tgsw_flagsave",false);
        //ユーザ名を取得
        userName=intent.getStringExtra("strUsr");
        //userName=intent.getStringExtra("strUsr");


          //  super.onCreate(savedInstanceState);
            //setContentView(R.layout.activity_gaiju);
            Button button = (Button) findViewById(R.id.button1);
            button.setOnClickListener(this);


        //イノシシ画像の出力
        ImageView imageView1 = (ImageView) findViewById(R.id.image_view_1);
        try {
            //画像データの取得
            InputStream istream = getResources().getAssets().open("shishi_1.bmp");
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            imageView1.setImageBitmap(bitmap);
        } catch (IOException e) {//例外(エラー)処理
            Log.d("Asetts", "Error");
        }

        //絶対パスから画像を表示する
        /*File imgfile = new File("D:\\tnct\\shishi_1.bmp");
        if(imgfile.exists()) {

            Bitmap imgBitmap = BitmapFactory.decodeFile(imgfile.getAbsolutePath());

            imageView1.setImageBitmap(imgBitmap);
        }*/

        //ロゴの出力
        ImageView imageView2 = (ImageView) findViewById(R.id.image_view_2);
        try {
            InputStream istream = getResources().getAssets().open("hatake_logo.png");
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            imageView2.setImageBitmap(bitmap);
        } catch (IOException e) {//例外(エラー)処理
            Log.d("Asetts", "Error");
        }

        /*String path="src/main/assets/logo.png";
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        Bitmap bmp=BitmapFactory.decodeFile(path,options);
        int height=options.outHeight;
        options.inSampleSize=height/600;

        options.inJustDecodeBounds=false;
        bmp=BitmapFactory.decodeFile(path,options);
        imageView2.setImageBitmap(bmp);*/

        //メンバ変数の初期化
        mBut_Data= (BootstrapButton) findViewById(R.id.but_data);
        mBut_Conf=(BootstrapButton) findViewById(R.id.but_conf);
        mUsrtxt=(TextView)findViewById(R.id.Usrtxt);
        //mTestText=(EditText) findViewById(R.id.TestText);
        //OnClick()のリスナ設定
        mBut_Conf.setOnClickListener(this);
        mBut_Data.setOnClickListener(this);

        //ユーザ名の表示　※要改良(見た目)--最低限の表示のみ実装
        if(userName==null){
            mUsrtxt.setText("設定画面でユーザ名を入力してください");
        }else {
            mUsrtxt.setText("あなたのユーザ名は"+userName+"です。");
        }

    }




    //インターフェイスの実装 OnClickListner()
    @Override
    public void onClick(View v) { //vはどのViewが押されたかを受け取る
        if(v.equals(mBut_Data)){ //データボタンが押されたときの動作
            //mTestText.setText("button push");　buttonデバッグ用
            //グラフ画面へのインテントを生成
            Intent intent = new Intent(this, DataActivity.class);
            intent.putExtra("strUsrfg",userName);
            //データ画面へと遷移
            startActivity(intent);
        }
        else if(v.equals(mBut_Conf)){//設定ボタンが押されたときの動作
            //トグルボタンの状態確認(デバッグ)
            /*Toast toast = Toast.makeText(GaijuActivity.this,"flagnotice's logic is"+tgsw_flagnotice,Toast.LENGTH_LONG);
            toast.show(); //デバッグ用のトースト*/
            //設定画面へのインテントを生成
            Intent intent = new Intent(this,ConfActivity.class);
            //設定画面へ持ち込むパラメータの指定
            intent.putExtra("tgsw_flagnotice",tgsw_flagnotice);
            intent.putExtra("tgsw_flagsave",tgsw_flagsave);
            intent.putExtra("strUsr",userName);
            //遷移する
            startActivity(intent);
        }
        //!!
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //クリックした時にintentを発行
        Intent notificationIntent = new Intent(this, GaijuActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                /*.setSmallIcon(R.drawable.ic_launcher) // アイコン*/
                .setTicker("Hello") // 通知バーに表示する簡易メッセージ
                .setWhen(System.currentTimeMillis()) // 時間
                .setContentTitle("My notification") // 展開メッセージのタイトル
                .setContentText("Hello Notification!!") // 展開メッセージの詳細メッセージ
                .setContentIntent(contentIntent) // PendingIntent
                .build();
        notificationManager.notify(1, notification);
        //!!
    }
}
