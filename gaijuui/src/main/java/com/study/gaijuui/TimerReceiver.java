package com.study.gaijuui;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Naoga on 2016/09/14.
 */

public class TimerReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent data) {
        Intent intent=new Intent(context,CallDialogActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);
        try {
            pendingIntent.send();
        }catch (PendingIntent.CanceledException e){
            e.printStackTrace();
        }
    }
}
