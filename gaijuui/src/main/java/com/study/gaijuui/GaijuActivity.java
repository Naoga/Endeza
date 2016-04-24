package com.study.gaijuui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.WindowDecorActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GaijuActivity extends AppCompatActivity implements View.OnClickListener{
    //widgetを格納するメンバ変数を定義
    private EditText mInputMessage;
    private Button mSendMessage;
    private LinearLayout mMessageLog;
    private TextView mCpuMessage;
    private TextView mUserMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaiju);

        //メンバ変数の初期化
        mInputMessage=(EditText)findViewById(R.id.input_message);
        mSendMessage=(Button) findViewById(R.id.send_message);
        mMessageLog=(LinearLayout)findViewById(R.id.message_log);
        mCpuMessage=(TextView)findViewById(R.id.cpu_message);
        mUserMessage=(TextView)findViewById(R.id.user_message);
        //ボタンを押したときの処理:リスナはthis
        mSendMessage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) { //vはどのViewがpushされたかを受け取る
        if(v.equals(mSendMessage)){ //SENDボタンがpushされたときの動作
            String inputText = mInputMessage.getText().toString();
            mUserMessage.setText(inputText);
            mCpuMessage.setText("それはいいですね");
            mInputMessage.setText("");
        }
    }
}
