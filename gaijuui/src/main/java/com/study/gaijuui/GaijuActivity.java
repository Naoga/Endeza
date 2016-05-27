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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class GaijuActivity extends Activity implements View.OnClickListener{
    //widgetを格納するメンバ変数の宣言
    private BootstrapButton mBut_Data;
    private BootstrapButton mBut_Conf;
    //private EditText mTestText ;
    //OnCreate()内に動作を記述する
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaiju);

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
        //mTestText=(EditText) findViewById(R.id.TestText);
        //OnClick()のリスナり設定
        mBut_Conf.setOnClickListener(this);
        mBut_Data.setOnClickListener(this);
    }

    //インターフェイスの実装 OnClickListner()
    @Override
    public void onClick(View v) { //vはどのViewが押されたかを受け取る
        if(v.equals(mBut_Data)){ //データボタンが押されたときの動作
            //mTestText.setText("button push");　buttonデバッグ用
            //画面遷移用のインテントの生成
            Intent intent = new Intent(this, DataActivity.class);
            //データ画面へと遷移
            startActivity(intent);
        }
        else if(v.equals(mBut_Conf)){//設定ボタンが押されたときの動作
            Intent intent = new Intent(this,ConfActivity.class);
            startActivity(intent);
        }
    }
}
