package com.study.gaijuui;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;

/**
 * Created by Naoga on 2016/09/14.
 */

public class CallDialogActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.show(getSupportFragmentManager(), "alert_dialog");
    }
}
