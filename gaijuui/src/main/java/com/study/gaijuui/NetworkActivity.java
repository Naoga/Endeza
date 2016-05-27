package com.study.gaijuui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;

public class NetworkActivity extends AppCompatActivity implements View.OnClickListener {
    private BootstrapButton mBut_ConfFromNet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
        mBut_ConfFromNet=(BootstrapButton)findViewById(R.id.but_conffromnet);
        mBut_ConfFromNet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBut_ConfFromNet)){
            Intent intent=new Intent(this,ConfActivity.class);
            startActivity(intent);
        }
    }
}
