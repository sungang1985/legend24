package com.nari.sungang.legendof24.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.nari.sungang.legendof24.R;

import info.hoang8f.widget.FButton;

/**
 * Created by sungang on 2015/12/31.
 */
public class MainActivity extends Activity {
    private FButton fButtonStart;
    private FButton fButtonIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fButtonStart = (FButton) findViewById(R.id.fbutton_start);
        fButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
        fButtonIntro = (FButton) findViewById(R.id.fbutton_intro);
    }
}
