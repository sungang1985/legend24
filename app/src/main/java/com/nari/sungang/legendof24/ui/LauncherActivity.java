package com.nari.sungang.legendof24.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nari.sungang.legendof24.R;
import com.nari.sungang.legendof24.adapter.LauncherPageAdapter;
import com.nari.sungang.legendof24.common.ILauncher;

/**
 * Created by sungang on 2016/4/23.
 */
public class LauncherActivity extends FragmentActivity implements ILauncher {
    private ViewPager viewPagerLauncher;
    private LauncherPageAdapter launcherPageAdapter;

    private ImageView[] imageViews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        if (!isFirstRunApp()) {
            intentMain();
        }
        viewPagerLauncher = (ViewPager) findViewById(R.id.viewpager_launcher);
        launcherPageAdapter = new LauncherPageAdapter(this, this);
        viewPagerLauncher.setOffscreenPageLimit(2);
        viewPagerLauncher.setCurrentItem(0);
        viewPagerLauncher.setAdapter(launcherPageAdapter);
        viewPagerLauncher.setOnPageChangeListener(onPageChangeListener);

        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.viewgroup_launcher);
        imageViews = new ImageView[4];
        for (int i = 0; i < imageViews.length; i++) {
            ImageView imageView = new ImageView(this);
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                imageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
            imageViews[i] = imageView;

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 10;
            layoutParams.rightMargin = 10;
            viewGroup.addView(imageView, layoutParams);
        }

    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setPageIndicatorBackground(position);

            TextView tvStartGame = (TextView) launcherPageAdapter.getViewList().get(position).findViewById(R.id.tv_sart_game);
            if (position == imageViews.length - 1) {
                tvStartGame.setVisibility(View.VISIBLE);
            } else {
                tvStartGame.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void setPageIndicatorBackground(int position) {
        for (int i = 0; i < imageViews.length; i++) {
            if (i == position) {
                imageViews[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                imageViews[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    @Override
    public void intentMain() {
        Intent intent = new Intent(this, BaseGameActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isFirstRunApp() {
        SharedPreferences sharedPreferences = getSharedPreferences("legend24", 0);
        boolean flag = sharedPreferences.getBoolean("FIRST", true);
        if (flag) {
            sharedPreferences.edit().putBoolean("FIRST", false).commit();
            return true;
        } else {
            return false;
        }

    }
}
