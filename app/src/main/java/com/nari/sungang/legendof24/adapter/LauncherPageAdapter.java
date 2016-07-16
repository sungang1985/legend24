package com.nari.sungang.legendof24.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nari.sungang.legendof24.R;
import com.nari.sungang.legendof24.common.ILauncher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sungang on 2016/4/23.
 */
public class LauncherPageAdapter extends PagerAdapter implements View.OnClickListener {
    private ILauncher iLauncher;
    private List<View> viewList;

    private int[] images = new int[]{R.mipmap.tutorial_1, R.mipmap.tutorial_2, R.mipmap.tutorial_3, R.mipmap.tutorial_4};

    public LauncherPageAdapter(Context context, ILauncher iLauncher) {
        viewList = new ArrayList<View>();
        this.iLauncher = iLauncher;

        for (int i = 0; i < images.length; i++) {
            View item = LayoutInflater.from(context).inflate(R.layout.activity_luancher_pager_item, null);
            ImageView imageView = (ImageView) item.findViewById(R.id.imageview_launcher);
            imageView.setImageResource(images[i]);
            item.findViewById(R.id.tv_sart_game).setOnClickListener(this);
            viewList.add(item);
        }

    }

    public List<View> getViewList() {
        return viewList;
    }

    @Override
    public int getCount() {
        return viewList == null ? 0 : viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(viewList.get(position), 0);
        return viewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(viewList.get(position));
    }

    @Override
    public void onClick(View v) {
        iLauncher.intentMain();
    }
}
