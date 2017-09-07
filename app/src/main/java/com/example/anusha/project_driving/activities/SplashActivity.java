  package com.example.anusha.project_driving.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.anusha.project_driving.R;
import com.example.anusha.project_driving.utils.SharedPrefer;

public class SplashActivity extends AppCompatActivity {

    CountDownTimer count;
    public static final int TIMEOUT = 2000;

    SharedPreferences mSharedPreference;
    SharedPreferences.Editor mEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSharedPreference = SplashActivity.this.getSharedPreferences("Anusha", Activity.MODE_PRIVATE);
        mEditor = mSharedPreference.edit();
        startTimer();
        count.start();

    }
    private void startTimer()
    {
        count = new CountDownTimer(TIMEOUT,700) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                SharedPrefer msharedPrefer = new SharedPrefer(SplashActivity.this);
                String username = msharedPrefer.getUsername();
                String password = msharedPrefer.getPassword();
                if(username.equals(""))
                {
                    Intent mIntent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(mIntent);
                    finish();
                }
                else{
                    Intent mIntent = new Intent(SplashActivity.this,DashBoardActivity.class);
                    startActivity(mIntent);
                    finish();
                }
            }
        };
    }



}
