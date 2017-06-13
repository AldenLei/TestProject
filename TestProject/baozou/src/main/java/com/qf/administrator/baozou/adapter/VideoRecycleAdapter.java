package com.qf.administrator.baozou.adapter;

import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qf.administrator.baozou.MyApp;
import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.activity.IndexItemActivity;
import com.qf.administrator.baozou.entity.LatestEntity;
import com.qf.administrator.baozou.views.ShareDialog;
import com.qf.administrator.baozou.views.ShareDialog.OnMyDialogClickListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import fm.jiecao.jcvideoplayer_lib.JCUtils;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by L_Alden on 2016/11/16.
 */
public class VideoRecycleAdapter extends RecyclerView.Adapter<VideoRecycleAdapter.ViewHolder> {

    private Context context;
    private List<LatestEntity.DataBean> list;

    public VideoRecycleAdapter(Context context, List<LatestEntity.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LatestEntity.DataBean bean = list.get(position);
        holder.customVideoplayerStandard.setUp(bean.getFile_url(), JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        Glide.with(context).load(bean.getImage()).error(R.mipmap.image_preview_video).centerCrop().into(holder.customVideoplayerStandard.thumbImageView);
        holder.tvVideoTitle.setText(bean.getTitle());
        holder.tvVideoComment.setText(bean.getComment_count() + "");
        holder.tvVideoVote.setText(bean.getVote_count() + "");
        holder.tvVideoPalycount.setText(bean.getPlay_count() + " 播放");
        holder.imgVideoShare.setTag(bean.getFile_url());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.custom_videoplayer_standard)
        JCVideoPlayerStandard customVideoplayerStandard;
        @InjectView(R.id.tv_video_title)
        TextView tvVideoTitle;
        @InjectView(R.id.tv_video_palycount)
        TextView tvVideoPalycount;
        @InjectView(R.id.img_video_share)
        ImageView imgVideoShare;
        @InjectView(R.id.img_video_message)
        ImageView imgVideoMessage;
        @InjectView(R.id.tv_video_comment)
        TextView tvVideoComment;
        @InjectView(R.id.img_video_like)
        ImageView imgVideoLike;
        @InjectView(R.id.tv_video_vote)
        TextView tvVideoVote;
        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            imgVideoShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showShareDialog((String)imgVideoShare.getTag());
                }
            });
        }
    }
    private void showShareDialog(final String url) {
        ShareDialog shareDialog = new ShareDialog(context);
        shareDialog.setOnMyDilogListener(new OnMyDialogClickListener() {
            @Override
            public void onShareQQ() {//分享QQ
                Log.e("TAG","response");
                //showShare();
                if (checkInstall("com.tencent.mobileqq")) {
                    shareUrl("com.tencent.mobileqq",
                            "com.tencent.mobileqq.activity.JumpActivity",
                            url);
                } else {
                    toInstallWebView("http://im.qq.com/mobileqq/");
                }
            }

            @Override
            public void onAddFavourity() {
                //加入数据库
               /* dataBaseEntity.setReadFlag("0");//是阅读还是收藏的Flag,阅读是1
                MyApp.getInstances().getDaoSession().getDataBaseEntityDao().update(dataBaseEntity);
                imgStarLove.setImageDrawable(getResources().getDrawable(R.mipmap.btn_nav_favourite_pre));*/
            }


            @Override
            public void onCopyLink() {//拷贝链接
                // 从API11开始android推荐使用android.content.ClipboardManager
                // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(url);
                Toast.makeText(context, "复制成功", Toast.LENGTH_SHORT).show();
            }
        });
        shareDialog.show();
    }
    /**
     * 是否安装分享app
     * @param packageName
     */
    public boolean checkInstall(String packageName){
        try {
            context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(context, "请先安装应用app", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * 跳转官方安装网址
     */
    public void toInstallWebView(String url){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }
    /**
     * 分享网页
     */
    public void shareUrl(String packageName,String className,String content){
        Intent intent =new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");
//      if(null != className && null != packageName && !TextUtils.isEmpty(className) && !TextUtils.isEmpty(packageName)){
//
//      }else {
//          if(null != packageName && !TextUtils.isEmpty(packageName)){
//              intent.setPackage(packageName);
//          }
//      }
        if(stringCheck(className) && stringCheck(packageName)){
            ComponentName componentName = new ComponentName(packageName, className);
            intent.setComponent(componentName);
        }else if(stringCheck(packageName)){
            intent.setPackage(packageName);
        }

        intent.putExtra(Intent.EXTRA_TEXT, content);

        Intent chooserIntent = Intent.createChooser(intent, "分享到：");
        context.startActivity(chooserIntent);
    }
    public static boolean stringCheck(String str){
        if(null != str && !TextUtils.isEmpty(str)){
            return true;
        }else {
            return false;
        }
    }
}
