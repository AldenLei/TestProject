package com.qf.administrator.baozou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.activity.IndexItemActivity;
import com.qf.administrator.baozou.constanse.Constances;
import com.qf.administrator.baozou.entity.LatestEntity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by L_Alden on 2016/11/16.
 */
public class UseRecycleAdapter extends RecyclerView.Adapter<UseRecycleAdapter.ViewHolder> {
    private Context context;
    private List<LatestEntity.DataBean> list;

    public UseRecycleAdapter(Context context, List<LatestEntity.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_tougao_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LatestEntity.DataBean bean = list.get(position);
        Glide.with(context).load(bean.getAuthor_avatar()).into(holder.imgUserAvatar);
        holder.tvUserTitle.setText(bean.getTitle());
        holder.tvAuthorName.setText(bean.getAuthor_name());
        holder.tvUserTitle.setTag(bean);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.img_user_avatar)
        ImageView imgUserAvatar;
        @InjectView(R.id.tv_user_title)
        TextView tvUserTitle;
        @InjectView(R.id.tv_author_name)
        TextView tvAuthorName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LatestEntity.DataBean bean = (LatestEntity.DataBean) tvUserTitle.getTag();
                    Intent intent = new Intent(context, IndexItemActivity.class);
                    intent.putExtra("comment_count", bean.getComment_count());
                    intent.putExtra("vote_count", bean.getVote_count());
                    intent.putExtra("url", Constances.shareHeadUrl+bean.getDocument_id());
                    intent.putExtra("title",bean.getTitle());
                    intent.putExtra("id",bean.getDocument_id());
                    intent.putExtra("authorName",bean.getAuthor_name());
                    intent.putExtra("timestamp",bean.getTimestamp());
                    intent.addFlags(Constances.USERTOUGAOFLAG);//0
                    context.startActivity(intent);
                }
            });
        }
    }
}
