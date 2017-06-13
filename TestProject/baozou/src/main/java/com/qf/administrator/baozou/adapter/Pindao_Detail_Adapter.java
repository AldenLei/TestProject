package com.qf.administrator.baozou.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.activity.Pindao_Detail_Activity;
import com.qf.administrator.baozou.entity.Pindao_Detail_Bean;
import com.qf.administrator.baozou.entity.Pindao_itemBean;

import java.util.List;

/**
 * Created by FHP on 2016/11/15.
 */
public class Pindao_Detail_Adapter extends BaseAdapter{
    private List<Pindao_Detail_Bean.DataBean> data;
    private Context context;
    public Pindao_Detail_Adapter (List<Pindao_Detail_Bean.DataBean> data, Context context){
        this.data=data;
        this.context=context;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.pindao_lv_item,parent,false);
            holder.img_head = (ImageView) convertView.findViewById(R.id.img_head);
            holder.tv_title = (TextView) convertView.findViewById(R.id.title);
            holder.tv_content= (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(holder);
        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }
        Pindao_Detail_Bean.DataBean dataBean = data.get(position);

        Glide.with(context).load(dataBean.getThumbnail()).into(holder.img_head);

        holder.tv_title.setText(dataBean.getTitle());
        holder.tv_content.setText(dataBean.getAuthor_name()+"|"+dataBean.getSection_name());
        return convertView;
    }
    class ViewHolder{
        ImageView img_head;
        TextView tv_title,tv_content;
    }
}
