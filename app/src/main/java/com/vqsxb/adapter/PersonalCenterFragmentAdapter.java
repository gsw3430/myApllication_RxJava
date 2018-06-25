package com.vqsxb.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 * des ： 个人中心fragment pageadapter  pageadapter  --Yalun
 */

public class PersonalCenterFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments;
    private List<String> mTabtitle;

    public PersonalCenterFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> mTabtitle) {
        super(fm);
        this.fragments = fragments;
        this.mTabtitle = mTabtitle;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabtitle.get(position);
    }
}
