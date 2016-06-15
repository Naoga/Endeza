package com.study.gaijuui;

/**
 * ステータスバーにNotificationを出す、メッセージを
 * 受け取ったら行う処理等を実装
 * バックグラウンドでこの処理を行うためのServiceを用意
 * Created by harada on 2016/05/11.
 */

import android.app.IntentService;
import android.app.NotificationManager;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import android.util.Log;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;


public class MyGcmIntentService extends IntentService{
    public static final String TAG = MyGcmIntentService.class.getSimpleName();

    public static final String SENDER_ID = "1015304688937";

    public MyGcmIntentService() {
        super("MyGcmIntentService service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            //GCM Registration Token取得
            String token = instanceID.getToken(SENDER_ID, GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.i(TAG, "GCM Registration Token: " + token);
        } catch (Exception e) {
            Log.e(TAG, "exception " + e.getMessage());
        }
    }
}


