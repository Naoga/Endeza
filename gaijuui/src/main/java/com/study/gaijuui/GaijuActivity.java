package com.study.gaijuui;

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
import com.beardedhen.androidbootstrap.TypefaceProvider;

import java.io.IOException;
import java.io.InputStream;

public class GaijuActivity extends AppCompatActivity /*implements View.OnClickListener*/{
    //widgetを格納するメンバ変数を定義
    /*private EditText mInputMessage;
    private Button mSendMessage;
    private LinearLayout mMessageLog;
    private TextView mCpuMessage;
    private TextView mUserMessage;*/

    //OnCreate()内に動作を記述する
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaiju);

        //イノシシ画像の出力
        ImageView imageView1 = (ImageView) findViewById(R.id.image_view_1);
        try {
            InputStream istream = getResources().getAssets().open("shishi_1.bmp");
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            imageView1.setImageBitmap(bitmap);
        } catch (IOException e) {
            Log.d("Asetts", "Error");
        }

        //ロゴの出力
        ImageView imageView2 = (ImageView) findViewById(R.id.image_view_2);
        try {
            InputStream istream = getResources().getAssets().open("logo.png");
            Bitmap bitmap = BitmapFactory.decodeStream(istream);
            imageView2.setImageBitmap(bitmap);
        } catch (IOException e) {
            Log.d("Asetts", "Error");
        }

        //メンバ変数の初期化
        /*mInputMessage=(EditText)findViewById(R.id.input_message);
        mSendMessage=(Button) findViewById(R.id.send_message);
        mMessageLog=(LinearLayout)findViewById(R.id.message_log);
        mCpuMessage=(TextView)findViewById(R.id.cpu_message);
        mUserMessage=(TextView)findViewById(R.id.user_message);
        //ボタンを押したときの処理:リスナはthis
        mSendMessage.setOnClickListener(this);*/

    }

    /*@Override
    public void onClick(View v) { //vはどのViewがpushされたかを受け取る
        if(v.equals(mSendMessage)){ //SENDボタンがpushされたときの動作
            String inputText = mInputMessage.getText().toString();
            mUserMessage.setText(inputText);
            mCpuMessage.setText("それはいいですね");
            mInputMessage.setText("");
        }
    }*/
}
