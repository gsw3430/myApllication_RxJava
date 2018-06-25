package com.vqsxb.fragment.search;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vqsxb.R;
import com.vqsxb.activity.SearchActivity;
import com.vqsxb.adapter.SearchListAdapter;
import com.vqsxb.bean.OnlineInfo;
import com.vqsxb.fragment.BaseFragment;
import com.vqsxb.interf.CommonCallBack;
import com.vqsxb.widget.CustomLoadMoreView;
import com.vqsxb.widget.RecycItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.vqsxb.httputils.ApiService.searchList;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class SearchListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.swipyRefreshLayout)
    SwipeRefreshLayout mSwipyRefreshLayout;

    private SearchActivity activity;
    // 关键词
    private String keyWord;
    // 页数
    private int page;
    // 集合
    private List<OnlineInfo> mList = new ArrayList<OnlineInfo>();
    private SearchListAdapter adapter;

    public SearchListFragment(SearchActivity activity) {
        this.activity = activity;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_online_rank;
    }

    @Override
    protected void initViews() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);
        mRecycler.addItemDecoration(new RecycItemDecoration(getActivity()).setBottom(R.dimen.x1));
        mSwipyRefreshLayout.setOnRefreshListener(this);
        mSwipyRefreshLayout.setColorSchemeResources(R.color.themeblue);
        adapter = new SearchListAdapter(getActivity(), mList);
        adapter.setEnableLoadMore(true);
        adapter.setLoadMoreView(new CustomLoadMoreView());
        adapter.setOnLoadMoreListener(this, mRecycler);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mRecycler.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    public void toSearch(String keyWord) {
        this.keyWord = keyWord;
        onRefresh();
    }

    @Override
    public void onRefresh() {
        page = 1;
        searchList(keyWord, page + "", adapter, mList, new CommonCallBack() {
            @Override
            public void onSuccess(String result) {
                mSwipyRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(String result) {
                mSwipyRefreshLayout.setRefreshing(false);
            }
        });


    }

    @Override
    public void onLoadMoreRequested() {
        page++ ;
        Log.e("+++>page", page+"");
        searchList(keyWord, page + "", adapter, mList, new CommonCallBack() {

            public void onSuccess(String result) {
                adapter.loadMoreComplete();
            }

            @Override
            public void onFailure(String result) {
                adapter.loadMoreEnd();
            }
        });
    }
}
