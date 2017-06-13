package com.qf.administrator.baozou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.entity.DataBaseEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Alden on 2017/5/11.
 */

public class ReadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<DataBaseEntity> list = new ArrayList<>();
    private Context context;

    public ReadAdapter(List<DataBaseEntity> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.read_adapter_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).tvReadTitle.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.tv_read_title)
        TextView tvReadTitle;
        @InjectView(R.id.ll_read_title)
        LinearLayout llReadTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            llReadTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
