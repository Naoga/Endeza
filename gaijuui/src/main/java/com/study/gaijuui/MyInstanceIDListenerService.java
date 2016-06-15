package com.study.gaijuui;
import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by harada on 2016/05/25.
 */
public class MyInstanceIDListenerService extends InstanceIDListenerService{
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        // 識別トークンを取得しなおす
        Intent intent = new Intent(this, MyGcmIntentService.class);
        startService(intent);
    }

}
