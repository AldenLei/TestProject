package com.qf.administrator.baozou.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.qf.administrator.baozou.MainActivity;
import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.activity.CommentActivity;
import com.qf.administrator.baozou.activity.LoginActivity;
import com.qf.administrator.baozou.entity.CommentBean;
import com.qf.administrator.baozou.utils.SharedPreferencesUtils;
import com.qf.administrator.baozou.utils.TimeUtils;
import com.qf.administrator.baozou.views.CircleImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by Alden on 2017/4/17.
 */

public class CommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<CommentBean.DataBean> dataBeanList = new ArrayList<>();

    //解析json,传个list
    public CommentAdapter(Context context, List<CommentBean.DataBean> list) {
        this.context = context;
        this.dataBeanList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_hot_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommentBean.DataBean bean = dataBeanList.get(position);
        final ViewHolder viewHolder = (ViewHolder)holder;
        Glide.with(context).load(bean.getUser().getReal_avatar_url()).asBitmap().placeholder(R.mipmap.avatar_feedback).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                viewHolder.cvCommentItemIcon.setImageBitmap(resource);
            }
        });
        viewHolder.tvCommentItemName.setText(bean.getUser().getName());
        if(bean.getParent()!=null){
            String temp = "<font color=\"#FB4859\">"+bean.getParent().getUser_name()+"</font>";
            viewHolder.tvCommentItemContent.setText("回复"+ Html.fromHtml(temp)+":"+bean.getContent());
        }else {
            viewHolder.tvCommentItemContent.setText(bean.getContent());
        }
        viewHolder.tvCommentTime.setText(TimeUtils.formatCommentTimeStamp(bean.getTime()));
        viewHolder.tvCommentLikeNum.setText(bean.getLikes()+"");
        viewHolder.llCommentItemHead.setTag(bean.getId()+"="+bean.getUser().getName());
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }


     class ViewHolder extends RecyclerView.ViewHolder{
        @InjectView(R.id.ll_comment_item_head)
        LinearLayout llCommentItemHead;
        @InjectView(R.id.tv_comment_item_content)
        TextView tvCommentItemContent;
        @InjectView(R.id.tv_comment_time)
        TextView tvCommentTime;
        @InjectView(R.id.ig_comment_reply)
        ImageView igCommentReply;
        @InjectView(R.id.tv_comment_reply)
        TextView tvCommentReply;
        @InjectView(R.id.ig_comment_like)
        ImageView igCommentLike;
        @InjectView(R.id.tv_comment_like_num)
        TextView tvCommentLikeNum;
         @InjectView(R.id.cv_comment_item_icon)
         CircleImageView cvCommentItemIcon;
         @InjectView(R.id.tv_comment_item_name)
         TextView tvCommentItemName;

        ViewHolder(final View view) {
            super(view);
            ButterKnife.inject(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    conmentMenu(view);
                }
            });
        }
         @OnClick({R.id.ll_comment_item_head, R.id.tv_comment_time, R.id.ig_comment_reply,
                 R.id.ig_comment_like,R.id.tv_comment_like_num,R.id.tv_comment_reply})
         public void onClick(View view) {
             switch (view.getId()) {
                 case R.id.ll_comment_item_head:
                     Log.e("TAG","点击头像");
                     break;
                 case R.id.tv_comment_time:
                     break;
                 case R.id.ig_comment_reply:
                     //回复

                     String idStr = (String) llCommentItemHead.getTag();
                     String[] strArr = idStr.split("=");
                     Intent intent = new Intent(context,CommentActivity.class);
                     intent.putExtra("id",strArr[0]);
                     intent.putExtra("name",strArr[1]);
                     Log.e("TAG1",strArr[0]+"="+strArr[1]);
                     context.startActivity(intent);
                     break;
                 case R.id.ig_comment_like:
                     //赞
                     break;
                 case R.id.tv_comment_like_num:

                     break;
                 case R.id.tv_comment_reply:
                     //回复
                     String idStr1 = (String) llCommentItemHead.getTag();
                     Log.e("TAG11",idStr1);
                     String[] strArr1 = idStr1.split("=");
                     Intent intent1 = new Intent(context,CommentActivity.class);
                     intent1.putExtra("id",strArr1[0]);
                     intent1.putExtra("name",strArr1[1]);
                     Log.e("TAG1",strArr1[0]+"="+strArr1[1]);
                     context.startActivity(intent1);
                     break;
             }
         }
         public  void conmentMenu(View view){
             PopupMenu popupMenu = new PopupMenu(context,view);

             ((Activity)context).getMenuInflater().inflate(R.menu.conment, popupMenu.getMenu());

             popupMenu.show();
             popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                 @Override
                 public boolean onMenuItemClick(MenuItem item) {
                     if(!SharedPreferencesUtils.isLogin(context)){//是否登录
                         context.startActivity(new Intent(context, LoginActivity.class));
                         Toast.makeText(context,"请先登录再评论",Toast.LENGTH_SHORT).show();
                     }else{
                     switch (item.getItemId()){
                         case R.id.menu_conment_huifu:
                            //回复
                             String idStr1 = (String) llCommentItemHead.getTag();
                             Log.e("TAG11",idStr1);
                             String[] strArr1 = idStr1.split("=");
                             Intent intent1 = new Intent(context,CommentActivity.class);
                             intent1.putExtra("id",strArr1[0]);
                             intent1.putExtra("name",strArr1[1]);
                             Log.e("TAG1",strArr1[0]+"="+strArr1[1]);
                             context.startActivity(intent1);
                         break;
                         case R.id.menu_conment_zan:
                             //赞
                             break;
                         case R.id.menu_conment_jubao:
                             //举报
                             break;
                     }
                     }
                     return false;
                 }
             });

         }
    }
}
