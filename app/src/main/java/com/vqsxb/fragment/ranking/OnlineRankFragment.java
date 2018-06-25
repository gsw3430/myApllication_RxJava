package com.vqsxb.fragment.ranking;

import android.annotation.SuppressLint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vqsxb.R;
import com.vqsxb.adapter.OnlineRankAdapter;
import com.vqsxb.bean.OnlineInfo;
import com.vqsxb.fragment.BaseFragment;
import com.vqsxb.fragment.RankingFragment;
import com.vqsxb.httputils.ApiService;
import com.vqsxb.interf.CommonCallBack;
import com.vqsxb.interf.RankHeadCallBack;
import com.vqsxb.utils.OtherUtil;
import com.vqsxb.widget.CustomLoadMoreView;
import com.vqsxb.widget.RankHeadView;
import com.vqsxb.widget.RecycItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/11/13 0013.
 * 联机排行
 */

@SuppressLint("ValidFragment")
public class OnlineRankFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.swipyRefreshLayout)
    SwipeRefreshLayout mSwipyRefreshLayout;

    private RankHeadView headView;
    private int category;//1=网游, 2=单机
    private int type;//1=下载榜, 2=新游榜, 3=预约榜, 4=畅销榜
    private int page = 1;
    private RankingFragment fragment;

    List<OnlineInfo> mList = new ArrayList<OnlineInfo>();
    OnlineRankAdapter adapter;

    @SuppressLint("ValidFragment")
    public OnlineRankFragment(RankingFragment fragment, int category, int type) {
        this.fragment = fragment;
        this.category = category;
        this.type = type;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_online_rank;
    }

    private void initHeadView(){
        headView = new RankHeadView(getActivity());
        headView.init(category,type);
        headView.setClickListener(new RankHeadCallBack() {
            @Override
            public void onClick(int category, int type) {
                fragment.setPager(category,type);
            }
        });
    }

    @Override
    protected void initViews() {
        initHeadView();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);
        mRecycler.addItemDecoration(new RecycItemDecoration(getActivity()).setBottom(R.dimen.x1));
        mSwipyRefreshLayout.setOnRefreshListener(this);
        mSwipyRefreshLayout.setColorSchemeResources(R.color.themeblue);
        adapter = new OnlineRankAdapter(getActivity(), mList);
        adapter.addHeaderView(headView);
        adapter.setEnableLoadMore(true);
        adapter.setLoadMoreView(new CustomLoadMoreView());
        adapter.setOnLoadMoreListener(this, mRecycler);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mRecycler.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (OtherUtil.isNotEmpty(adapter)){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void initData() {
        ApiService.getRanking(category+"", type+"", page+"", adapter, mList, new CommonCallBack() {
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
    public void onRefresh() {
        page = 1;
        initData();
    }

    @Override
    public void onLoadMoreRequested() {
        page++;
        ApiService.getRanking(category+"", type+"", page+"", adapter, mList, new CommonCallBack() {
            @Override
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
