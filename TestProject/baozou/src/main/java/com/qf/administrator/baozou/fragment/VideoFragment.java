package com.qf.administrator.baozou.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.adapter.VideoRecycleAdapter;
import com.qf.administrator.baozou.callback.ResultCallBack;
import com.qf.administrator.baozou.constanse.Constances;
import com.qf.administrator.baozou.entity.LatestEntity;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import okhttp3.Call;

/**
 * Created by L_Alden on 2016/11/14.
 */
public class VideoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.video_recycleview)
    RecyclerView videoRecycleview;
    @InjectView(R.id.video_swiperefresh)
    SwipeRefreshLayout videoSwiperefresh;
    private LatestEntity latestEntity;
    private List<LatestEntity.DataBean> list = new ArrayList<>();
    private VideoRecycleAdapter recycleAdapter;
    private String latestTitleId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View videoFrag = inflater.inflate(R.layout.videofragment_layout, null);
        ButterKnife.inject(this, videoFrag);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        videoRecycleview.setLayoutManager(linearLayoutManager);
        videoSwiperefresh.setColorSchemeColors(Color.RED);
        videoSwiperefresh.setOnRefreshListener(this);
        recycleAdapter = new VideoRecycleAdapter(getContext(), list);
        videoRecycleview.setAdapter(recycleAdapter);

        OkHttpUtils.get().url(Constances.videoUrl).build().execute(new ResultCallBack<LatestEntity>() {
            @Override

            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(LatestEntity response, int id) {
                if (response != null) {
                    latestEntity = response;
                    List<LatestEntity.DataBean> data = response.getData();
                    latestTitleId = ""+data.get(0).getDocument_id();
                    list.addAll(data);
                    recycleAdapter.notifyDataSetChanged();
                }
            }
        });
        videoRecycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //用来标记是否正在向最后一个滑动
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(final RecyclerView recyclerView, int newState) {
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
                        OkHttpUtils.get().url(Constances.videoNextUrl + latestEntity.getTimestamp()).build().execute(new ResultCallBack<LatestEntity>() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(LatestEntity response, int id) {
                                if (response != null) {
                                    latestEntity = response;
                                    list.addAll(list.size() - 1, response.getData());
                                    recycleAdapter.notifyDataSetChanged();
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
        return videoFrag;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }

    @Override
    public void onRefresh() {
        OkHttpUtils.get().url(Constances.videoUrl).build().execute(new ResultCallBack<LatestEntity>() {
            @Override

            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(LatestEntity response, int id) {
                if (response != null) {
                    latestEntity = response;
                    List<LatestEntity.DataBean> data = response.getData();
                    String titleID = ""+data.get(0).getDocument_id();
                    if(latestTitleId.equals(titleID)){
                        Toast.makeText(getActivity(),"已是最新数据",Toast.LENGTH_SHORT).show();
                        videoSwiperefresh.setRefreshing(false);
                        return;
                    }
                    latestTitleId = titleID;
                    list.addAll(data);
                    recycleAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(),"刷新完成",Toast.LENGTH_SHORT).show();
                    videoSwiperefresh.setRefreshing(false);
                }
            }
        });
    }
}
