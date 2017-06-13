package com.qf.administrator.baozou.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qf.administrator.baozou.MainActivity;
import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.activity.AddArticleActivity;
import com.qf.administrator.baozou.activity.SettingActivity;
import com.qf.administrator.baozou.adapter.MyIndexFragmentAdapter;
import com.qf.administrator.baozou.utils.NetworkUtil;
import com.qf.administrator.baozou.utils.SharedPreferencesUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by L_Alden on 2016/11/15.
 */
public class DawerIndexFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {
    @InjectView(R.id.img_opendraw)
    ImageView imgOpendraw;
    @InjectView(R.id.img_more)
    ImageView imgMore;
    @InjectView(R.id.img_bell)
    ImageView imgBell;
    @InjectView(R.id.img_write)
    ImageView imgWrite;
    @InjectView(R.id.tv_no_network)
    TextView tvNoNetwork;
    @InjectView(R.id.index_tablayou)
    TabLayout indexTablayou;
    @InjectView(R.id.index_viewpager)
    ViewPager indexViewpager;
    @InjectView(R.id.draw_right)
    LinearLayout drawRight;
    @InjectView(R.id.rl_main_head_bar)
    RelativeLayout rlMainHeadBar;

    private ImageView igPublishLink;

    private ImageView igPublishArticle;
    private List<Fragment> list = new ArrayList<>();
    private MyIndexFragmentAdapter adapter;

    private IndexFragment indexFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dawer_index_fragment, null);
        ButterKnife.inject(this, view);
        indexFragment = new IndexFragment();
        list.add(indexFragment);
        list.add(new UserTougaoFragment());
        list.add(new VideoFragment());
        indexTablayou.addTab(indexTablayou.newTab(), 0, true);
        indexTablayou.addTab(indexTablayou.newTab(), 1, false);
        indexTablayou.addTab(indexTablayou.newTab(), 2, false);

        adapter = new MyIndexFragmentAdapter(getActivity().getSupportFragmentManager(), list);
        //初始化ViewPager
        indexViewpager.setAdapter(adapter);
        indexTablayou.setupWithViewPager(indexViewpager);
        //给tab设置title  必须在和viewpager绑定过后设置title，不然title为空
        indexTablayou.getTabAt(0).setText("首页");
        indexTablayou.getTabAt(1).setText("用户投稿");
        indexTablayou.getTabAt(2).setText("视频");
        if (!NetworkUtil.isConnectWork(getContext())) {
            tvNoNetwork.setVisibility(View.VISIBLE);
        } else {
            tvNoNetwork.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.img_opendraw, R.id.img_more, R.id.img_bell, R.id.img_write})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_opendraw:
                ((MainActivity) getActivity()).openOrCloseDrawer();
                break;
            case R.id.img_more:
                PopupMenu popupMenu = new PopupMenu(getContext(), imgMore);
                if (SharedPreferencesUtils.isDayTheme(getContext())) {//日间还是夜间
                    getActivity().getMenuInflater().inflate(R.menu.more, popupMenu.getMenu());
                } else {
                    getActivity().getMenuInflater().inflate(R.menu.more_day, popupMenu.getMenu());
                }
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(this);
                break;
            case R.id.img_bell:
                //关于自己的消息界面
                break;
            case R.id.img_write:
                //发布稿文按钮
                publishNews();
                break;

        }
    }

    private void publishNews() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popView = layoutInflater.inflate(R.layout.article_write_layout, null);
        final PopupWindow popupWindow = new PopupWindow(getContext());
        popupWindow.setContentView(popView);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(imgWrite, Gravity.NO_GRAVITY, 0, 215);
        igPublishLink = (ImageView) popView.findViewById(R.id.ig_publish_link);
        igPublishArticle = (ImageView) popView.findViewById(R.id.ig_publish_article);
        igPublishArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                startActivity(new Intent(getContext(), AddArticleActivity.class));
            }
        });
        igPublishLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void changeTheme(boolean isSaveStatus, Context context) {
        if (SharedPreferencesUtils.isDayTheme(context)) {
            if (isSaveStatus) {
                SharedPreferencesUtils.saveTheme(context, "isDayTheme", "1");
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.backgroud_red));
            }
            rlMainHeadBar.setBackgroundColor(getResources().getColor(R.color.backgroud_red));
            indexTablayou.setBackgroundColor(getResources().getColor(R.color.background_white));
            indexTablayou.setTabTextColors(getResources().getColor(R.color.backgroud_red),
                    getResources().getColor(R.color.backgroud_red));
            indexTablayou.setSelectedTabIndicatorColor(getResources().getColor(R.color.backgroud_red));
            drawRight.setBackgroundColor(getResources().getColor(R.color.color_Light_text_backGray));
            indexFragment.changeTheme(false);
        } else {
            if (isSaveStatus) {
                SharedPreferencesUtils.saveTheme(context, "isDayTheme", "0");
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getActivity().getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.color_Light_text_gray));
            }
            rlMainHeadBar.setBackgroundColor(getResources().getColor(R.color.color_Light_text_gray));
            indexTablayou.setBackgroundColor(getResources().getColor(R.color.color_Light_text_gray));
            indexTablayou.setTabTextColors(getResources().getColor(R.color.background_white),
                    getResources().getColor(R.color.background_white));
            indexTablayou.setSelectedTabIndicatorColor(getResources().getColor(R.color.background_white));
            drawRight.setBackgroundColor(getResources().getColor(R.color.background_white));

            indexFragment.changeTheme(true);
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                indexFragment.onRefresh();
                break;
            case R.id.menu_night_theme:
                ((MainActivity) getActivity()).changeTheme(true);
                break;
            case R.id.menu_day_theme:
                ((MainActivity) getActivity()).changeTheme(true);
                break;
            case R.id.menu_setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }
        return false;
    }

}
