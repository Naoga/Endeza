package com.study.gaijuui;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

public class ConfActivity extends AppCompatActivity implements View.OnClickListener {

    //メンバ変数の宣言
    private BootstrapButton mBut_TopFromConf;
    private Button mNetowrk_Conf;
    private Button mPict_Delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf);
        //各メンバ変数を各ウィジェット用にキャスト
        mBut_TopFromConf=(BootstrapButton) findViewById(R.id.but_topfromconf);
        mNetowrk_Conf=(Button)findViewById(R.id.network_conf);
        mPict_Delete=(Button)findViewById(R.id.pict_delete);
        //OnClick()のリスナに設定
        mBut_TopFromConf.setOnClickListener(this);
        mNetowrk_Conf.setOnClickListener(this);
        mPict_Delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBut_TopFromConf)){//トップページに戻るボタンが押されたら
            //インテントの生成
            Intent intent = new Intent(this, GaijuActivity.class);
            //画面遷移
            startActivity(intent);
        }
        else if(v.equals(mNetowrk_Conf)){
            //ネットワーク設定画面の遷移
            Intent intent=new Intent(this,NetworkActivity.class);
            startActivity(intent);
        }
        else if(v.equals(mPict_Delete)) {
            //ダイアログ用フィールドの生成
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(ConfActivity.this);
            //ダイアログのタイトル
            alertDialog.setTitle("注意!");
            //ダイアログに表示するメッセージ
            alertDialog.setMessage("すべての写真を消去しても良いですか？");
            //"消去する"と表示
            alertDialog.setPositiveButton("消去する", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {//消去すると押されたら
                    //ここに写真を消去するメソッドを書く
                    //通知バナーの生成
                    Toast toast = Toast.makeText(ConfActivity.this,"写真を消去しました",Toast.LENGTH_LONG);
                    //バナーを表示
                    toast.show();
                }
            });
            //以下上記と同様
            alertDialog.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast toast = Toast.makeText(ConfActivity.this,"キャンセルしました",Toast.LENGTH_LONG);
                    toast.show();
                }
            });
            //通知バナーの表示
            alertDialog.create().show();
        }

    }
}
