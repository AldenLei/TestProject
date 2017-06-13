package com.qf.administrator.baozou.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class HelpVPAdapter extends FragmentPagerAdapter{
    private List<Fragment> pager;
    public HelpVPAdapter(FragmentManager fm, List<Fragment> pager) {
        super(fm);
        this.pager = pager;
    }

    @Override
    public Fragment getItem(int position) {
        return pager.get(position);
    }

    @Override
    public int getCount() {
        return pager.size();
    }
}
