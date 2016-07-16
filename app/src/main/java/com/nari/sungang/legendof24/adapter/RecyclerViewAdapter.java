package com.nari.sungang.legendof24.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.nari.sungang.legendof24.R;
import com.nari.sungang.legendof24.model.Operation;

import java.util.List;

/**
 * Created by sungang on 2016/1/3.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.OperationViewHolder> {
    private static OnItemClickListener listener;
    private List<Operation> operationList;
    private Context context;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public RecyclerViewAdapter(List<Operation> operationList, Context context) {
        this.operationList = operationList;
        this.context = context;
    }


    static class OperationViewHolder extends RecyclerView.ViewHolder {
        ImageButton imageButton;

        public OperationViewHolder(final View itemView) {
            super(itemView);
            imageButton = (ImageButton) itemView.findViewById(R.id.operation);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(itemView, getLayoutPosition());
                    }
                }
            });
        }
    }

    @Override
    public OperationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.operation_item, parent, false);
        OperationViewHolder operationViewHolder = new OperationViewHolder(view);
        return operationViewHolder;
    }

    @Override
    public void onBindViewHolder(OperationViewHolder holder, final int position) {
        holder.imageButton.setImageResource(operationList.get(position).getResId());
    }

    @Override
    public int getItemCount() {
        return operationList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
}
