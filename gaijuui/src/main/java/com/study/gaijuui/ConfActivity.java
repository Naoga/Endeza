package com.study.gaijuui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.beardedhen.androidbootstrap.BootstrapButton;

public class ConfActivity extends AppCompatActivity implements View.OnClickListener {

    private BootstrapButton mBut_TopFromConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conf);
        mBut_TopFromConf=(BootstrapButton) findViewById(R.id.but_topfromconf);
        mBut_TopFromConf.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBut_TopFromConf)){
            Intent intent = new Intent(this, GaijuActivity.class);
            startActivity(intent);
        }
    }
}
