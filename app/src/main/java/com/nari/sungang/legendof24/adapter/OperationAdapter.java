package com.nari.sungang.legendof24.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.nari.sungang.legendof24.R;
import com.nari.sungang.legendof24.model.Operation;

import java.util.List;

/**
 * Created by sungang on 2016/1/3.
 */
public class OperationAdapter extends ArrayAdapter<Operation> {
    private int resource;

    public OperationAdapter(Context context, int resource, List<Operation> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Operation operation = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resource, null);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.operation);
        imageButton.setImageResource(operation.getResId());
        return view;
    }
}
