package com.vqsxb.activity;

import com.vqsxb.R;
import com.vqsxb.fragment.DiscoveryFragment;
import com.vqsxb.fragment.InformationFragment;
import com.vqsxb.fragment.MineFragment;
import com.vqsxb.fragment.RankingFragment;
import com.vqsxb.fragment.RecommendFragment;

public enum MainTab {

	/*
	NEWS(0, R.string.main_tab_name_news, R.drawable.tab_icon_new,
			NewsViewPagerFragment.class),
			*/

    RECOMMENT(0, R.string.tab_name_recomment, R.drawable.tab_recommend_selector,
            RecommendFragment.class),

    RANKING(1, R.string.tab_name_ranking, R.drawable.tab_ranking_selector,
            RankingFragment.class),

    DISCOVERY(2, R.string.tab_name_discovery, R.drawable.tab_discovery_selector,
            DiscoveryFragment.class),

    INFORMATION(3, R.string.tab_name_info, R.drawable.tab_information_selector,
            InformationFragment.class),

    MINE(4, R.string.tab_name_mine, R.drawable.tab_mine_selector,
            MineFragment.class);

    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    private MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
