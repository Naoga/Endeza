package com.study.gaijuui;

/*
    通知を受けとるためのブロードキャストレシーバー
 * スリープ時でもBroadcastの処理を確実に実行できるように、
 * レシーバーはWakefulBroadcastReceiverクラスを継承して実装する
 */

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver{
    public void onReceive(Context context,Intent intent){
            /*受け取ったインテントの処理をGcmIntentServiceで行う*/
        ComponentName comp=new ComponentName(context.getPackageName(),
               /*Mykesi*/ MyGcmIntentService.class.getName());
        /*サービスの起動。処理用スリープを制御*/
        startWakefulService(context,(intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);
    }
}
