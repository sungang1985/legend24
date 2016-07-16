package com.nari.sungang.legendof24.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ImageView;

import com.nari.sungang.legendof24.R;
import com.squareup.picasso.Picasso;

/**
 * Created by sungang on 2015/12/31.
 */
public class WelcomeActivity extends AppCompatActivity {
    ImageView welcomeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        welcomeImageView = (ImageView) findViewById(R.id.imageview_welcome);
        initImageView();
    }

    private void initImageView() {
        Picasso.with(this).load(R.mipmap.welcome).into(welcomeImageView);
        CountDownTimer timer = new CountDownTimer(3100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        };
        timer.start();
    }
}
