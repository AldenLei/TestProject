package com.qf.administrator.baozou.fragment;

import android.annotation.SuppressLint;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.adapter.CommentAdapter;
import com.qf.administrator.baozou.callback.ResultCallBack;
import com.qf.administrator.baozou.entity.CommentBean;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.Call;

/**
 * Created by Alden on 2017/4/25.
 */

@SuppressLint("ValidFragment")
public class CommentFragment extends Fragment {
    @InjectView(R.id.rv_comment_fragemnt)
    RecyclerView rvCommentFragemnt;
    @InjectView(R.id.ig_placeholder_loading)
    ImageView igPlaceholderLoading;
    @InjectView(R.id.rl_loading_anim)
    RelativeLayout rlLoadingAnim;


    private CommentAdapter commentAdapter;
    private String doucumentId;
    private List<CommentBean.DataBean> beanList = new ArrayList<>();
    private AnimationDrawable animDrawable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.comment_fragment, null);

        ButterKnife.inject(this, view);
        doucumentId = getArguments().getString("id");
        commentAdapter = new CommentAdapter(getContext(), beanList);
        LinearLayoutManager linearLayoutManagerHot = new LinearLayoutManager(getContext());
        linearLayoutManagerHot.setOrientation(OrientationHelper.VERTICAL);
        rvCommentFragemnt.setLayoutManager(linearLayoutManagerHot);
        rvCommentFragemnt.setAdapter(commentAdapter);
        return view;
    }



    @Override
    public void onStart() {
        super.onStart();
        animDrawable = (AnimationDrawable) igPlaceholderLoading.getDrawable();
        animDrawable.start();
        Log.e("TAG",doucumentId);
        OkHttpUtils.get().url(doucumentId).build().execute(new ResultCallBack<CommentBean>() {

            @Override
            public void onError(Call call, Exception e, int id) {
                Log.e("TAG","错误");
            }

            @Override
            public void onResponse(CommentBean response, int id) {
                Log.e("TAG","1111");
                beanList.addAll(response.getData());
                commentAdapter.notifyDataSetChanged();
                animDrawable.stop();
                rlLoadingAnim.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
