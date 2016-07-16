package com.nari.sungang.legendof24.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.nari.sungang.legendof24.R;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by sungang on 2016/1/2.
 */

public class ImageViewAdapter extends BaseAdapter {
    private static Random random = new Random();
    private static Map<Integer, String> pokerMap = new HashMap<Integer, String>() {
    };
    private static int[] pokers = {
            R.drawable.clbace, R.drawable.clb02, R.drawable.clb03, R.drawable.clb04, R.drawable.clb05,
            R.drawable.clb06, R.drawable.clb07, R.drawable.clb08, R.drawable.clb09,
            R.drawable.clb10, R.drawable.clbjack, R.drawable.clbqueen, R.drawable.clbking,
            R.drawable.hrtace, R.drawable.hrt02, R.drawable.hrt03, R.drawable.hrt04, R.drawable.hrt05,
            R.drawable.hrt06, R.drawable.hrt07, R.drawable.hrt08, R.drawable.hrt09,
            R.drawable.hrt10, R.drawable.hrtjack, R.drawable.hrtqueen, R.drawable.hrtking,
            R.drawable.spdace, R.drawable.spd02, R.drawable.spd03, R.drawable.spd04, R.drawable.spd05,
            R.drawable.spd06, R.drawable.spd07, R.drawable.spd08, R.drawable.spd09,
            R.drawable.spd10, R.drawable.spdjack, R.drawable.spdqueen, R.drawable.spdking,
            R.drawable.dndace, R.drawable.dnd02, R.drawable.dnd03, R.drawable.dnd04, R.drawable.dnd05,
            R.drawable.dnd06, R.drawable.dnd07, R.drawable.dnd08, R.drawable.dnd09,
            R.drawable.dnd10, R.drawable.dndjack, R.drawable.dndqueen, R.drawable.dndking};

    static {
        for (int i = 0; i < pokers.length; i++) {
            if ((i + 1) % 13 == 0) {
                pokerMap.put(pokers[i], "13");
            } else {
                pokerMap.put(pokers[i], (i + 1) % 13 + "");
            }
        }
    }

    private Context mContext;
    private int widthPixels;
    private int heightPixels;

    public ImageViewAdapter(Context mContext, int widthPixels, int heightPixels) {
        this.mContext = mContext;
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(widthPixels / 2, heightPixels / 2));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //imageView.setPadding(5, 5, 5, 5);
        } else {
            imageView = (ImageView) convertView;
        }
        int rand = random.nextInt(52);
        String value = pokerMap.get(pokers[rand]);
        Picasso.with(mContext).load(pokers[rand]).into(imageView);
        imageView.setTag(value);
        return imageView;
    }

}
