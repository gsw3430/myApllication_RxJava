package com.vqsxb.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vqsxb.R;
import com.vqsxb.fragment.ranking.CompanyRankFragment;
import com.vqsxb.fragment.ranking.OnlineRankFragment;
import com.vqsxb.widget.VqsViewPager;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class RankingFragment extends BaseFragment {

    @BindView(R.id.viewPager)
    VqsViewPager mViewPager;

    private int category = 1;//1=网游, 2=单机
    private int type = 1;//1=下载榜, 2=新游榜, 3=预约榜, 4=畅销榜

    // fragment集合
    private ArrayList<Fragment> mFragments = new ArrayList<Fragment>();;
    // viewpage适配器
    private RankPagerAdaper adaper;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_ranking;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

        while (category<3){
            if (type<5){
                mFragments.add(new OnlineRankFragment(this,category,type));
                type++;
            }else{
                type = 1;
                category++;
            }
        }
        mFragments.add(new CompanyRankFragment(this,category,type));

        adaper = new RankPagerAdaper(getFragmentManager());
        mViewPager.setAdapter(adaper);
    }

    public void setPager(int category,int type){
        mViewPager.setCurrentItem((category-1)*4+type-1);
    }

    class RankPagerAdaper extends FragmentPagerAdapter {

        public RankPagerAdaper(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
