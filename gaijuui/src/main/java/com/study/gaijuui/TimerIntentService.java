package com.study.gaijuui;

/**
 * Created by Naoga on 2016/09/14.
 */
import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;

public class TimerIntentService extends IntentService{
    public TimerIntentService(String name){
        super(name);
    }

    public TimerIntentService(){
        super("TimerIntentService");
    }

    @Override
    protected void onHandleIntent(Intent data) {
        SystemClock.sleep(5000);
        Intent intent=new Intent();
        intent.setAction("TIMER_FINISHED");
        sendBroadcast(intent);
    }
}
