package com.qf.administrator.baozou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
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

import java.util.List;

/**
 * Created by L_Alden on 2016/11/14.
 */
public class AdViewpagerAdapter extends PagerAdapter {

    private Context context;
    private List<LatestEntity.TopStoriesBean> latestList;

    public AdViewpagerAdapter(Context context, List<LatestEntity.TopStoriesBean> topStoriesBeen) {
        this.context = context;
        this.latestList = topStoriesBeen;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position = position%latestList.size();
        if(position<0){
            position = latestList.size()+position;
        }
        View view = LayoutInflater.from(context).inflate(R.layout.ad_item_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_ad);
        TextView textView = (TextView) view.findViewById(R.id.tv_ad_title);
        final LatestEntity.TopStoriesBean topStoriesBean = latestList.get(position);
        Glide.with(context).load(topStoriesBean.getImage()).placeholder(R.mipmap.aa_photo_empty).into(imageView);
        textView.setText(topStoriesBean.getTitle());
        //如果view存在父组件，就要先移除
        ViewParent vp =view.getParent();
        if (vp!=null){
            ViewGroup parent = (ViewGroup)vp;
            parent.removeView(view);
        }

        container.addView(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击存储阅读数据库
                DataBaseEntity dataBaseEntity = new DataBaseEntity((long)topStoriesBean.getDocument_id(),""+topStoriesBean.getDocument_id(),topStoriesBean.getDisplay_type(),topStoriesBean.getTitle()
                        ,topStoriesBean.getImage(),topStoriesBean.getUrl(),topStoriesBean.getUrl(),topStoriesBean.getTimestamp()+"","0",
                        topStoriesBean.getComment_count()+"",topStoriesBean.getVote_count()+"",topStoriesBean.getSection_name(),
                        topStoriesBean.getAuthor_id()+"",topStoriesBean.getAuthor_name(),topStoriesBean.getAuthor_avatar(),"1");
                if(MyApp.getInstances().getDaoSession().getDataBaseEntityDao().queryBuilder().where(DataBaseEntityDao.Properties.Id.eq((long)topStoriesBean.getDocument_id())).list().size()==0&& !TextUtils.isEmpty(dataBaseEntity.getTitle())){
                    MyApp.getInstances().getDaoSession().getDataBaseEntityDao().insert(dataBaseEntity);
                }
                Intent intent = new Intent(context, IndexItemActivity.class);
                intent.putExtra("databean",dataBaseEntity);
                intent.putExtra("url", topStoriesBean.getUrl());
                intent.putExtra("vote_count", topStoriesBean.getVote_count());
                intent.putExtra("comment_count", topStoriesBean.getComment_count());
                intent.putExtra("id",topStoriesBean.getDocument_id());
                if(topStoriesBean.getUrl()!=null&&topStoriesBean.getUrl().contains("weixin.qq.com")){
                    intent.addFlags(Constances.WEIXINURLFLAG);//1
                    Log.e("提示","微信文章");
                }else{
                    Log.e("提示","暴走文章"+topStoriesBean.toString());
                    intent.putExtra("title",topStoriesBean.getTitle());
                    Log.e("title",topStoriesBean.getTitle());
                    intent.putExtra("bgImage",topStoriesBean.getImage());
                    intent.putExtra("avatar",topStoriesBean.getAuthor_avatar());
                    intent.putExtra("authorName",topStoriesBean.getAuthor_name());
                    intent.putExtra("timestamp",topStoriesBean.getTimestamp());
                    intent.addFlags(Constances.BAOZOUURLFLAG);//0
                }
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
         // super.destroyItem(container, position, object);
        //由于我们在instantiateItem()方法中已经处理了remove的逻辑，因此这里并不需要处理。
        // 实际上，实验表明这里如果加上了remove的调用，则会出现ViewPager的内容为空的情况。
        //container.removeView((View) object);
    }
}
