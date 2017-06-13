package com.qf.administrator.baozou.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.adapter.IndexRecycleViewAdapter;
import com.qf.administrator.baozou.callback.ResultCallBack;
import com.qf.administrator.baozou.constanse.Constances;
import com.qf.administrator.baozou.entity.LatestEntity;
import com.qf.administrator.baozou.utils.SharedPreferencesUtils;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by L_Alden on 2016/11/14.
 */
public class IndexFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.index_recycleview)
    RecyclerView indexRecycleview;
    @InjectView(R.id.index_swiperefresh)
    SwipeRefreshLayout indexSwiperefresh;


    private IndexRecycleViewAdapter recycleViewAdapter;
    private List<LatestEntity.DataBean> listData = new ArrayList<>();

    private List<LatestEntity.DataBean> data;
    private LatestEntity latestEntity;

    private String latestTitleId;//最新的一篇文章id

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View indexFrag = inflater.inflate(R.layout.indexfragment_layout, null);
        ButterKnife.inject(this, indexFrag);

        indexSwiperefresh.setColorSchemeColors(Color.RED);
        indexSwiperefresh.setOnRefreshListener(this);
        //设置LinearLayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        indexRecycleview.setLayoutManager(linearLayoutManager);
        recycleViewAdapter = new IndexRecycleViewAdapter(getContext(), listData);
        indexRecycleview.setAdapter(recycleViewAdapter);
        //获取到广告条
        OkHttpUtils.get().url(Constances.doucumentUrl).build().execute(new ResultCallBack<LatestEntity>() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(LatestEntity response, int id) {
                latestEntity = response;
                final List<LatestEntity.TopStoriesBean> top_stories = response.getTop_stories();
                data = response.getData();
                Log.e("info", "" + top_stories.size() + "==" + data.size());
                latestTitleId = "" + data.get(0).getDocument_id();//第一条新闻

                if (top_stories != null && top_stories.size() != 0) {

                    recycleViewAdapter.addAd(top_stories);

                    listData.addAll(data);
                    recycleViewAdapter.notifyDataSetChanged();
                }
            }
        });

        indexRecycleview.addOnScrollListener(new OnScrollListener() {
            //用来标记是否正在向最后一个滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滚动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完全显示的ItemPosition
                    int lastVisibleItem = manager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = manager.getItemCount();

                    // 判断是否滚动到底部，并且是向右滚动
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        //加载更多功能的代码
                        Toast.makeText(getContext(), "到最后item", Toast.LENGTH_SHORT).show();
                        //加载下一页数据
                        OkHttpUtils.get().url(Constances.nextPageUrl + latestEntity.getTimestamp()).build().execute(new ResultCallBack<LatestEntity>() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(LatestEntity response, int id) {
                                if (response != null) {
                                    latestEntity = response;
                                    listData.addAll(listData.size() - 1, response.getData());
                                    recycleViewAdapter.notifyDataSetChanged();
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //dx用来判断横向滑动方向，dy用来判断纵向滑动方向
                if (dy > 0) {
                    //大于0表示正在向右滚动
                    isSlidingToLast = true;
                } else {
                    //小于等于0表示停止或向左滚动
                    isSlidingToLast = false;
                }
            }
        });

        return indexFrag;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onRefresh() {
        OkHttpUtils.get().url(Constances.doucumentUrl).build().execute(new ResultCallBack<LatestEntity>() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(LatestEntity response, int id) {
                latestEntity = response;
                final List<LatestEntity.TopStoriesBean> top_stories = response.getTop_stories();
                data = response.getData();
                Log.e("info", "" + top_stories.size() + "==" + data.size());
                String titleID = data.get(0).getDocument_id() + "";
                if (latestTitleId.equals(titleID)) {
                    Toast.makeText(getActivity(), "已是最新数据", Toast.LENGTH_SHORT).show();
                    indexSwiperefresh.setRefreshing(false);
                    return;
                }
                latestTitleId = titleID;
                if (top_stories != null && top_stories.size() != 0) {
                    recycleViewAdapter.addAd(top_stories);

                    listData.addAll(data);
                    recycleViewAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                    indexSwiperefresh.setRefreshing(false);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void changeTheme(boolean isDay){
        if(recycleViewAdapter==null){
            return;
        }
        recycleViewAdapter.changeTheme(isDay);
        recycleViewAdapter.notifyDataSetChanged();
    }
}
