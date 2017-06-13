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
import com.qf.administrator.baozou.adapter.UseRecycleAdapter;
import com.qf.administrator.baozou.callback.ResultCallBack;
import com.qf.administrator.baozou.constanse.Constances;
import com.qf.administrator.baozou.entity.LatestEntity;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by L_Alden on 2016/11/14.
 */
public class UserTougaoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    @InjectView(R.id.user_recycleview)
    RecyclerView userRecycleview;
    @InjectView(R.id.user_swiperefresh)
    SwipeRefreshLayout userSwiperefresh;

    private LatestEntity latestEntity;
    private UseRecycleAdapter recycleAdapter;
    private List<LatestEntity.DataBean> dataBeen = new ArrayList<>();
    private String latestTitleId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View userFrag = inflater.inflate(R.layout.userfragment_layout, null);
        ButterKnife.inject(this, userFrag);
        userSwiperefresh.setColorSchemeColors(Color.RED);
        userSwiperefresh.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        userRecycleview.setLayoutManager(linearLayoutManager);
        recycleAdapter = new UseRecycleAdapter(getContext(), dataBeen);
        userRecycleview.setAdapter(recycleAdapter);

        OkHttpUtils.get().url(Constances.userTougaoUrl).build().execute(new ResultCallBack<LatestEntity>() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(LatestEntity response, int id) {
                if (response != null) {
                    latestEntity = response;
                    List<LatestEntity.DataBean> data = response.getData();
                    latestTitleId = ""+data.get(0).getDocument_id();
                    dataBeen.addAll(data);
                    recycleAdapter.notifyDataSetChanged();
                }
            }
        });

        userRecycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                        OkHttpUtils.get().url(Constances.userNextPageUrl + latestEntity.getTimestamp()).build().execute(new ResultCallBack<LatestEntity>() {
                            @Override
                            public void onError(Call call, Exception e, int id) {

                            }

                            @Override
                            public void onResponse(LatestEntity response, int id) {
                                if (response != null) {
                                    latestEntity = response;
                                    dataBeen.addAll(dataBeen.size() - 1, response.getData());
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
        return userFrag;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onRefresh() {
        OkHttpUtils.get().url(Constances.userTougaoUrl).build().execute(new ResultCallBack<LatestEntity>() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }
            @Override
            public void onResponse(LatestEntity response, int id) {
                if (response != null) {
                    latestEntity = response;
                    List<LatestEntity.DataBean> data = response.getData();
                    String titleID = data.get(0).getDocument_id()+"";
                    if(latestTitleId.equals(titleID)){
                        Toast.makeText(getActivity(),"已是最新数据",Toast.LENGTH_SHORT).show();
                        userSwiperefresh.setRefreshing(false);
                        return;
                    }
                    latestTitleId = titleID;
                    dataBeen.addAll(data);
                    recycleAdapter.notifyDataSetChanged();
                    Toast.makeText(getActivity(),"刷新完成",Toast.LENGTH_SHORT).show();
                    userSwiperefresh.setRefreshing(false);
                }
            }
        });
    }
}
