package com.magostinhojr.changebuttons;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by marcelo.agostinho on 8/11/16.
 */
public class MyBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action) {
            case Intent.ACTION_SCREEN_OFF:
                BaseActivity.unlockScreen();
                break;

            case Intent.ACTION_SCREEN_ON:
                // and do whatever you need to do here
                BaseActivity.clearScreen();
        }
    }
}