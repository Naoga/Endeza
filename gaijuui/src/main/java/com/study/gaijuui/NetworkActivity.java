package com.study.gaijuui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkActivity extends AppCompatActivity implements View.OnClickListener {
    String strUsrtn;
    private BootstrapButton mBut_ConfFromNet;
    private Button mbutton1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        Intent intent=getIntent();
        strUsrtn=intent.getStringExtra("strUsrtn");
        mBut_ConfFromNet=(BootstrapButton)findViewById(R.id.but_conffromnet);
        mBut_ConfFromNet.setOnClickListener(this);
        /*mbutton1=(Button)findViewById(R.id.button1);
        mbutton1.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBut_ConfFromNet)){
            Intent intent=new Intent(this,ConfActivity.class);
            intent.putExtra("strUsr",strUsrtn);
            startActivity(intent);
        }
        /*else if(v.equals(mbutton1)){
            String address=
                    ((EditText)findViewById(R.id.txtIPAdress)).getText().toString();
            String strPort=
                    ((EditText)findViewById(R.id.txtPort)).getText().toString();
            int port=Integer.parseInt(strPort);
            Socket socket=null;

            try{
                socket=new Socket(address,port);
                PrintWriter pw =new PrintWriter(socket.getOutputStream(),true);
                String sendTxt =
                        ((EditText)findViewById(R.id.txtSend)).getText().toString();
                pw.println(sendTxt);
            }catch (UnknownHostException e){
                e.printStackTrace();
                /*Toast.makeText(this, "サーバーとの接続に失敗しました。",
                        Toast.LENGTH_SHORT).show();*/
            /*}catch (IOException e){
                e.printStackTrace();
                /*Toast.makeText(this, "サーバーとの接続に失敗しました。",
                        Toast.LENGTH_SHORT).show();*/
            /*}

            if(socket != null){
                try{
                    socket.close();
                    socket=null;
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }*/
    }
}
