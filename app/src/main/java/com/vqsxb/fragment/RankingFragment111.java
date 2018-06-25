package com.vqsxb.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.vqsxb.R;
import com.vqsxb.adapter.MyFragmentPagerAdapter;
import com.vqsxb.fragment.ranking.StandRankFragment;
import com.vqsxb.widget.HeadTabLayout;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class RankingFragment111 extends BaseFragment {


    @BindView(R.id.rankHeadTab)
    HeadTabLayout mRankHeadTab;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    // fragment集合
    private ArrayList<Fragment> mFragments;
    // viewpage适配器
    private MyFragmentPagerAdapter adapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_ranking1111;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {
        mFragments = new ArrayList<Fragment>();
        // mFragments.add(new OnlineRankFragment());
        mFragments.add(new StandRankFragment());
       //  mFragments.add(new CompanyRankFragment());
        String titles[] = getResources().getStringArray(R.array.ranklist_tabs);

        adapter = new MyFragmentPagerAdapter(getChildFragmentManager(), mFragments, Arrays.asList(titles));
        mViewPager.setAdapter(adapter);
        mRankHeadTab.isSetBg = true;
        mRankHeadTab.setViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);
    }
}
