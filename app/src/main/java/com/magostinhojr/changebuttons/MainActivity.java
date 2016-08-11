package com.magostinhojr.changebuttons;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    private TextView textView;

    KeyguardManager keyguardManager;
    KeyguardManager.KeyguardLock lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_view);

        keyguardManager = (KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE);
        lock = keyguardManager.newKeyguardLock(KEYGUARD_SERVICE);
        lock.disableKeyguard();

        IntentFilter filter = new IntentFilter( Intent.ACTION_SCREEN_ON );
        filter.addAction( Intent.ACTION_SCREEN_OFF );
        registerReceiver(new MyBroadcast(), filter);

    }

    @Override
    protected void onPause() {
        super.onPause();

        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);

        activityManager.moveTaskToFront(getTaskId(), 0);

        count();
        lock.reenableKeyguard();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lock.disableKeyguard();
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_IMMERSIVE);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_IMMERSIVE);

        return super.onTouchEvent(event);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        Log.i("TESTE", String.valueOf(keyCode));

        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            Log.i("TESTE", "BOTAO VOLUME UP");
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            Log.i("TESTE", "BOTAO VOLUME DOWN");
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.i("TESTE", "BOTAO BACK");
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_POWER) {
            Log.i("TESTE", "BOTAO POWER");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_POWER) {
            Log.i("", "Dispath event power");
            Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            sendBroadcast(closeDialog);
            return true;
        }

        return super.dispatchKeyEvent(event);
    }

    private Integer i = 0;
    public void count(){
        textView.setText(String.valueOf(i++));
    }


}
