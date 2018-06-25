package com.vqsxb.fragment.search;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.vqsxb.R;
import com.vqsxb.activity.SearchActivity;
import com.vqsxb.fragment.BaseFragment;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class SearchThirdFragment extends BaseFragment {

    @BindView(R.id.tv_key)
    TextView mTvKey;
    @BindView(R.id.third_search_list_recyclerview)
    RecyclerView mThirdSearchListRecyclerview;
    @BindView(R.id.thirdRefreshLayout)
    SwipeRefreshLayout mThirdRefreshLayout;

    private SearchActivity activity;

    private int type;
    private int page;
    private String key;

    public SearchThirdFragment(SearchActivity activity) {
        this.activity = activity;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_search_third;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData() {

    }

    public void search(String key, int type) {
        this.key = key;
        this.type = type;

        mTvKey.setText("没有搜索到结果" + key);

       /* thirdlist.clear();
        thirdAdapter.setNewData(thirdlist);
        thridEmptyView.showNone();
        thirdRefreshLayout.setRefreshing(true);
        thirdRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                onRefresh();
            }
        },500);*/
    }
}
