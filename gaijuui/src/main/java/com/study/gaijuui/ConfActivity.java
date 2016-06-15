package com.study.gaijuui;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.beardedhen.androidbootstrap.BootstrapButton;

public class ConfActivity extends AppCompatActivity implements View.OnClickListener , CompoundButton.OnCheckedChangeListener{

    //メンバ変数の宣言
    private BootstrapButton mBut_TopFromConf;
    private Button mNetowrk_Conf;
    private Button mPict_Delete;
    private Button mUser_Conf;
    private Switch mTb_Notice;
    private Switch mTb_Save;
    //トグルスイッチ用フラグ
    boolean tgsw_flagnotice=true;
    boolean tgsw_flagsave;
    String strUsr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf);

        Intent intent=getIntent();
        tgsw_flagnotice=intent.getBooleanExtra("tgsw_flagnotice",true);
        tgsw_flagsave=intent.getBooleanExtra("tgsw_flagsave",false);
        strUsr=intent.getStringExtra("strUsr");

        //各メンバ変数を各ウィジェット用にキャスト
        mBut_TopFromConf=(BootstrapButton) findViewById(R.id.but_topfromconf);
        mNetowrk_Conf=(Button)findViewById(R.id.network_conf);
        mPict_Delete=(Button)findViewById(R.id.pict_delete);
        mUser_Conf=(Button)findViewById(R.id.user_conf);
        mTb_Notice=(Switch) findViewById(R.id.tb_notice);
        mTb_Save=(Switch)findViewById(R.id.tb_save);

        //OnClick()のリスナに設定
        mBut_TopFromConf.setOnClickListener(this);
        mNetowrk_Conf.setOnClickListener(this);
        mPict_Delete.setOnClickListener(this);
        mUser_Conf.setOnClickListener(this);
        //onCheckedChangeListner()のリスナに設定
        mTb_Notice.setOnCheckedChangeListener(this);
        mTb_Save.setOnCheckedChangeListener(this);

        //通知がオフなら
        if(!tgsw_flagnotice){
            //トグルスイッチをオフに書き換え
            mTb_Notice.setChecked(false);
        }
        //省電力設定がオンなら
        if(tgsw_flagsave){
            //トグルスイッチをオンに書き換え
            mTb_Save.setChecked(true);
        }

    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBut_TopFromConf)){//トップページに戻るボタンが押されたら
            //インテントの生成
            Intent intent = new Intent(this, GaijuActivity.class);
            intent.putExtra("tgsw_flagnotice",tgsw_flagnotice);
            intent.putExtra("tgsw_flagsave",tgsw_flagsave);
            intent.putExtra("strUsr",strUsr);
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
        else if(v.equals(mUser_Conf)){
            //ユーザ名の設定(未完成)
            LayoutInflater inflater=(LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
            final View layout=inflater.inflate(R.layout.userconf_dialog,
                    (ViewGroup)findViewById(R.id.layout_root));
            AlertDialog.Builder builder=new AlertDialog.Builder(ConfActivity.this);
            builder.setTitle("ユーザ名を入力してください");
            builder.setView(layout);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText userName=(EditText)layout.findViewById(R.id.username);
                    //String strUser=userName.set
                    //userName.setText(strUsr);
                    strUsr=userName.getText().toString();
                    Toast.makeText( ConfActivity.this, "ユーザ名:"+strUsr+"を登録", Toast.LENGTH_SHORT).show();
                }
            });
            builder.setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();
            /*Toast toast = Toast.makeText(ConfActivity.this,"ボタンが押されたよ",Toast.LENGTH_LONG);
            toast.show(); //デバッグ用のトースト*/
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView.getId() == R.id.tb_notice){
            tgsw_flagnotice=isChecked;
            /*Toast toast = Toast.makeText(ConfActivity.this,"clicked tb_notice:"+tgsw_flagnotice,Toast.LENGTH_LONG);
            toast.show();*/ //デバッグ用のトースト
            //通知がオフに設定されたら
            if(isChecked==false){
                //通知を切る動作を記述
            }
        }
        else if(buttonView.getId() == R.id.tb_save){
            tgsw_flagsave=isChecked;
            /*Toast toast = Toast.makeText(ConfActivity.this,"clicked tb_save:"+tgsw_flagsave,Toast.LENGTH_LONG);
            toast.show();*/ //デバッグ用のトースト
            if(isChecked==true){
                //省電力(機能の停止)を行う動作を記述
            }
        }
    }
}
