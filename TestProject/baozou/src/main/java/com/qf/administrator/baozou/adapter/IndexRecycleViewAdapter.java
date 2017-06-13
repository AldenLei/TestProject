package com.qf.administrator.baozou.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anye.greendao.gen.DataBaseEntityDao;
import com.bumptech.glide.Glide;
import com.qf.administrator.baozou.MyApp;
import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.activity.IndexItemActivity;
import com.qf.administrator.baozou.constanse.Constances;
import com.qf.administrator.baozou.entity.DataBaseEntity;
import com.qf.administrator.baozou.entity.LatestEntity;
import com.qf.administrator.baozou.utils.SharedPreferencesUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by L_Alden on 2016/11/15.
 */
public class IndexRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LatestEntity.DataBean> list = new ArrayList<>();
    private Context context;
    private AdViewpagerAdapter adViewpagerAdapter;
    private List<LatestEntity.TopStoriesBean> imageList = new ArrayList<>();
    private ViewHolder2 viewHolder2;

    public IndexRecycleViewAdapter(Context context, List<LatestEntity.DataBean> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 2) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.index_recycleview_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        } else if (viewType == 1) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.index_recycleview_item2, parent, false);
            ViewHolder2 viewHolder = new ViewHolder2(view);
            viewHolder2 = viewHolder;
            return viewHolder;
        } else if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.index_recycle_first_item, parent, false);
            ViewHolderFirst viewHolderFirst = new ViewHolderFirst(view);
            return viewHolderFirst;
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        LatestEntity.DataBean dataBean = list.get(position);
        if (holder instanceof ViewHolder) {
            Glide.with(context).load(dataBean.getThumbnail()).into(((ViewHolder) holder).imgRecycleview);
            Glide.with(context).load(dataBean.getAuthor_avatar()).into(((ViewHolder) holder).imgRecycleviewIcon);
            ((ViewHolder) holder).tvItemTitle.setText(dataBean.getTitle());
            ((ViewHolder) holder).tvItemTitle.setTag(dataBean);
            List<LatestEntity.DataBean.RecommendersBean> recommenders = dataBean.getRecommenders();
            if (recommenders != null) {
                ((ViewHolder) holder).tvAuthor.setText(recommenders.get(0).getName());
            }
        }
        if (holder instanceof ViewHolder2) {

            ((ViewHolder2) holder).tvItemTitle.setTag(dataBean);
            Glide.with(context).load(dataBean.getThumbnail()).into(((ViewHolder2) holder).imgRecycleview);
            ((ViewHolder2) holder).tvItemTitle.setText(dataBean.getTitle());
            ((ViewHolder2) holder).tvAuthor.setText(dataBean.getAuthor_name() + " | " + dataBean.getSection_name());
            if(SharedPreferencesUtils.isDayTheme(context)){
                ((ViewHolder2) holder).tvItemTitle.setTextColor(Color.parseColor("#4d4b4b"));
                ((ViewHolder2) holder).itemView.setBackgroundColor(context.getResources().getColor(R.color.background_white));
            }else{
                ((ViewHolder2) holder).tvItemTitle.setTextColor(context.getResources().getColor(R.color.background_white));
                ((ViewHolder2) holder).itemView.setBackgroundColor(context.getResources().getColor(R.color.color_Light_text_gray));
            }
        }
        if (holder instanceof ViewHolderFirst) {
            ((ViewHolderFirst) holder).adViewpager.setAdapter(adViewpagerAdapter);
        }
    }

    public void addAd(List<LatestEntity.TopStoriesBean> list) {
        imageList = list;
        adViewpagerAdapter = new AdViewpagerAdapter(context, list);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        }
        return list.get(position).getDisplay_type();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void changeTheme(boolean isDay){
        if(isDay){
            viewHolder2.tvItemTitle.setTextColor(Color.parseColor("#4d4b4b"));
            viewHolder2.itemView.setBackgroundColor(context.getResources().getColor(R.color.background_white));
        }else{
            viewHolder2.tvItemTitle.setTextColor(context.getResources().getColor(R.color.background_white));
            viewHolder2.itemView.setBackgroundColor(context.getResources().getColor(R.color.color_Light_text_backGray));
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.img_recycleview)
        ImageView imgRecycleview;
        @InjectView(R.id.tv_item_title)
        TextView tvItemTitle;
        @InjectView(R.id.img_recycleview_icon)
        ImageView imgRecycleviewIcon;
        @InjectView(R.id.tv_author)
        TextView tvAuthor;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("提示","click1");
                    LatestEntity.DataBean bean = (LatestEntity.DataBean) tvItemTitle.getTag();
                    //点击存储阅读数据库
                    DataBaseEntity dataBaseEntity = new DataBaseEntity((long)bean.getDocument_id(),""+bean.getDocument_id(),bean.getDisplay_type(),bean.getTitle()
                            ,bean.getImage(),bean.getUrl(),bean.getUrl(),bean.getTimestamp()+"",bean.getPlay_count(),
                            bean.getComment_count()+"",bean.getVote_count()+"",bean.getSection_name(),
                            bean.getAuthor_id()+"",bean.getAuthor_name(),bean.getAuthor_avatar(),"1");
                    if(MyApp.getInstances().getDaoSession().getDataBaseEntityDao().queryBuilder().where(DataBaseEntityDao.Properties.Id.eq((long)bean.getDocument_id())).list().size()==0&& !TextUtils.isEmpty(dataBaseEntity.getTitle())){
                        MyApp.getInstances().getDaoSession().getDataBaseEntityDao().insert(dataBaseEntity);
                    }
                    Intent intent = new Intent(context, IndexItemActivity.class);
                    intent.putExtra("databean",dataBaseEntity);
                    intent.putExtra("comment_count", bean.getComment_count());
                    intent.putExtra("vote_count", bean.getVote_count());
                    intent.putExtra("url", bean.getUrl());
                    intent.putExtra("id",bean.getDocument_id());
                    if(bean.getUrl()!=null&&bean.getUrl().contains("weixin.qq.com")){
                        intent.addFlags(Constances.WEIXINURLFLAG);//1
                        Log.e("提示","微信文章");
                    }else{
                        Log.e("提示","暴走文章"+bean.toString());
                        intent.putExtra("title",bean.getTitle());
                        Log.e("title",bean.getTitle());

                        intent.putExtra("bgImage",bean.getImage());
                        intent.putExtra("avatar",bean.getAuthor_avatar());
                        intent.putExtra("authorName",bean.getAuthor_name());
                        intent.putExtra("timestamp",bean.getTimestamp());
                        intent.addFlags(Constances.BAOZOUURLFLAG);//0
                    }

                    context.startActivity(intent);
                }
            });
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        @InjectView(R.id.img_recycleview)
        ImageView imgRecycleview;
        @InjectView(R.id.tv_item_title)
        TextView tvItemTitle;
        @InjectView(R.id.tv_author)
        TextView tvAuthor;

        ViewHolder2(View view) {
            super(view);
            ButterKnife.inject(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("提示","click");
                    LatestEntity.DataBean bean = (LatestEntity.DataBean) tvItemTitle.getTag();
                    //点击存储阅读数据库
                    DataBaseEntity dataBaseEntity = new DataBaseEntity((long)bean.getDocument_id(),""+bean.getDocument_id(),bean.getDisplay_type(),bean.getTitle()
                    ,bean.getImage(),bean.getUrl(),bean.getUrl(),bean.getTimestamp()+"",bean.getPlay_count(),
                            bean.getComment_count()+"",bean.getVote_count()+"",bean.getSection_name(),
                            bean.getAuthor_id()+"",bean.getAuthor_name(),bean.getAuthor_avatar(),"1");
                    if(MyApp.getInstances().getDaoSession().getDataBaseEntityDao().queryBuilder().where(DataBaseEntityDao.Properties.Id.eq((long)bean.getDocument_id())).list().size()==0&& !TextUtils.isEmpty(dataBaseEntity.getTitle())){
                        MyApp.getInstances().getDaoSession().getDataBaseEntityDao().insert(dataBaseEntity);
                    }
                    //然后跳转详情界面
                    Intent intent = new Intent(context, IndexItemActivity.class);
                    intent.putExtra("databean",dataBaseEntity);
                    intent.putExtra("comment_count", bean.getComment_count());
                    intent.putExtra("vote_count", bean.getVote_count());
                    intent.putExtra("url", bean.getUrl());
                    intent.putExtra("id",bean.getDocument_id());
                    if(bean.getUrl()!=null&&bean.getUrl().contains("weixin.qq.com")){
                        intent.addFlags(Constances.WEIXINURLFLAG);//1
                        Log.e("提示","微信文章");
                    }else{
                        Log.e("提示","暴走文章"+bean.toString());
                        intent.putExtra("title",bean.getTitle());
                        Log.e("title",bean.getTitle());
                        intent.putExtra("bgImage",bean.getImage());
                        intent.putExtra("avatar",bean.getAuthor_avatar());
                        intent.putExtra("authorName",bean.getAuthor_name());
                        intent.putExtra("timestamp",bean.getTimestamp());
                        intent.addFlags(Constances.BAOZOUURLFLAG);//0
                    }
                    context.startActivity(intent);
                }
            });
        }
    }

    class ViewHolderFirst extends RecyclerView.ViewHolder {
        public ImageHandler handler = new ImageHandler(new WeakReference<ViewHolderFirst>(ViewHolderFirst.this));

        @InjectView(R.id.ad_viewpager)
        ViewPager adViewpager;
        ViewHolderFirst(View view) {
            super(view);
            ButterKnife.inject(this, view);
            adViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                //配合Adapter的currentItem字段进行设置。
                @Override
                public void onPageSelected(int arg0) {
                    handler.sendMessage(Message.obtain(handler, ImageHandler.MSG_PAGE_CHANGED, arg0, 0));
                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {
                }

                //覆写该方法实现轮播效果的暂停和恢复
                @Override
                public void onPageScrollStateChanged(int arg0) {
                    switch (arg0) {
                        case ViewPager.SCROLL_STATE_DRAGGING:
                            handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
                            break;
                        case ViewPager.SCROLL_STATE_IDLE:
                            handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
                            break;
                        default:
                            break;
                    }
                }
            });
            //默认在中间附近，使用户看不到边界
            int mid = Integer.MAX_VALUE/2;
            //初始显示第一个
            if(imageList.size()!=0) {
                adViewpager.setCurrentItem(mid - mid / imageList.size());
            }
            else{
                adViewpager.setCurrentItem(mid);
            }
            //开始轮播效果
            handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
        }
    }
}

//处理广告条循环滚动的handler
class ImageHandler extends Handler{

    /**
     * 请求更新显示的View。
     */
    protected static final int MSG_UPDATE_IMAGE  = 1;
    /**
     * 请求暂停轮播。
     */
    protected static final int MSG_KEEP_SILENT   = 2;
    /**
     * 请求恢复轮播。
     */
    protected static final int MSG_BREAK_SILENT  = 3;
    /**
     * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
     * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
     * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
     */
    protected static final int MSG_PAGE_CHANGED  = 4;

    //轮播间隔时间
    protected static final long MSG_DELAY = 3000;

    //使用弱引用避免Handler泄露.这里的泛型参数可以不是Activity，也可以是Fragment等
    private WeakReference<IndexRecycleViewAdapter.ViewHolderFirst> weakReference;
    private int currentItem = 0;

    public ImageHandler(WeakReference<IndexRecycleViewAdapter.ViewHolderFirst> wk){
        weakReference = wk;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Log.d("info", "receive message " + msg.what);
        IndexRecycleViewAdapter.ViewHolderFirst viewHolderFirst = weakReference.get();
        if (viewHolderFirst==null){
            //Activity已经回收，无需再处理UI了
            return ;
        }
        //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
        if (viewHolderFirst.handler.hasMessages(MSG_UPDATE_IMAGE)){
            viewHolderFirst.handler.removeMessages(MSG_UPDATE_IMAGE);

        }
        switch (msg.what) {
            case MSG_UPDATE_IMAGE:
                currentItem++;
                viewHolderFirst.adViewpager.setCurrentItem(currentItem);
                //准备下次播放
                viewHolderFirst.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                break;
            case MSG_KEEP_SILENT:
                //只要不发送消息就暂停了
                break;
            case MSG_BREAK_SILENT:
                viewHolderFirst.handler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                break;
            case MSG_PAGE_CHANGED:
                //记录当前的页号，避免播放的时候页面显示不正确。
                currentItem = msg.arg1;
                break;
            default:
                break;
        }
    }
}
