package com.example.instabookr;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by mukesh on 30/7/16.
 */
public class SplashActivity extends AppCompatActivity {

    private static final int AUTO_HIDE_DELAY_MILLIS = 6000;
    CountDownTimer cdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // hide status bar must be called before adding content
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);

        // hiding ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_splash);

         cdt = new CountDownTimer(AUTO_HIDE_DELAY_MILLIS, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                if (Common.IS_USER_LOGGED_IN == false) {
//                    btn_register.setVisibility(View.VISIBLE);
                } else {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        cdt.start();
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        cdt.cancel();
//    }

}
