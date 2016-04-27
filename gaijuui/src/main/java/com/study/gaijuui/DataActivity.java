package com.study.gaijuui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class DataActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBut_TopFromData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dataactivity);

        mBut_TopFromData=(Button)findViewById(R.id.but_topfromdata);
        mBut_TopFromData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBut_TopFromData)) {
            Intent intent = new Intent(this, GaijuActivity.class);
            startActivity(intent);
        }
    }
}
