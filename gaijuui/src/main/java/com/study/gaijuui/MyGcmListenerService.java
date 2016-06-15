package com.study.gaijuui;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;


/**
 * Created by harada on 2016/05/25.
 */

public class MyGcmListenerService extends GcmListenerService{
    public static final String TAG = MyGcmIntentService.class.getSimpleName();

    @Override
    public void onMessageReceived(String from, Bundle data) {
        super.onMessageReceived(from, data);
        // 送信元プロジェクト番号
        Log.v(TAG, "from=" + from);
        // 送信されたメッセージ
        Log.v(TAG, "message=" + data.get("message"));
    }
}


