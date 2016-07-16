package com.nari.sungang.legendof24.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nari.sungang.legendof24.R;

/**
 * Created by sungang on 2016/5/7.
 */
public class BaseGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base_game);
    }
}
