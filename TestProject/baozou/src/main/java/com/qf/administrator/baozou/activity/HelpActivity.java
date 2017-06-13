package com.qf.administrator.baozou.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.adapter.HelpVPAdapter;
import com.qf.administrator.baozou.fragment.FanKuiFragment;
import com.qf.administrator.baozou.fragment.QuestionFragment;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends AppCompatActivity implements View.OnClickListener {
    public TabLayout mTabLayout;
    private ViewPager viewPager;
    private HelpVPAdapter adapter;
    private List<Fragment> pager;
    private ImageView help_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        help_back = (ImageView)findViewById(R.id.help_back);
        help_back.setOnClickListener(this);
        mTabLayout = (TabLayout)findViewById(R.id.tablayout);
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        initFragment();
        adapter = new HelpVPAdapter(getSupportFragmentManager(),pager);
        viewPager.setAdapter(adapter);
        initTab();


    }
    public void initFragment()
    {
        pager = new ArrayList<>();
        pager.add(new FanKuiFragment());
        pager.add(new QuestionFragment());
    }

    private void initTab() {


        mTabLayout.setupWithViewPager(viewPager);//关联ViewPager
        mTabLayout.removeAllTabs();
        mTabLayout.addTab(mTabLayout.newTab().setText("意见反馈"));
        mTabLayout.addTab(mTabLayout.newTab().setText("常见问题"));

    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
