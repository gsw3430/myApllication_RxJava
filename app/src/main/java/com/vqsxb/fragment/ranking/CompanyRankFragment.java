package com.vqsxb.fragment.ranking;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.vqsxb.R;
import com.vqsxb.adapter.CompanyRankAdapter;
import com.vqsxb.bean.CompanyInfo;
import com.vqsxb.fragment.BaseFragment;
import com.vqsxb.fragment.RankingFragment;
import com.vqsxb.interf.RankHeadCallBack;
import com.vqsxb.widget.RankHeadView;
import com.vqsxb.widget.RecycItemDecoration;
import com.vqsxb.widget.refresh.SwipyRefreshLayout;
import com.vqsxb.widget.refresh.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alibaba.fastjson.JSON.parseArray;
import static com.alibaba.fastjson.JSON.parseObject;
import static com.vqsxb.httputils.ApiService.xBService;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

@SuppressLint("ValidFragment")
public class CompanyRankFragment extends BaseFragment implements SwipyRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.swipyRefreshLayout)
    SwipyRefreshLayout mSwipyRefreshLayout;

    private int category;//1=网游, 2=单机
    private int type;//1=下载榜, 2=新游榜, 3=预约榜, 4=畅销榜
    private int page = 1;
    private RankingFragment fragment;

    List<CompanyInfo> mList = new ArrayList<>();
    CompanyRankAdapter adapter;
    private RankHeadView headView;

    @SuppressLint("ValidFragment")
    public CompanyRankFragment(RankingFragment fragment, int category, int type) {
        this.fragment = fragment;
        this.category = category;
        this.type = type;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_recomment;
    }


    private void initHeadView() {
        headView = new RankHeadView(getActivity());
        headView.init(category, type);
        headView.setClickListener(new RankHeadCallBack() {
            @Override
            public void onClick(int category, int type) {
                fragment.setPager(category, type);
            }
        });
    }

    @Override
    protected void initViews() {
        initHeadView();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);
        mRecycler.addItemDecoration(new RecycItemDecoration(getActivity()).setBottom(R.dimen.x10));
        mSwipyRefreshLayout.setOnRefreshListener(this);
        mSwipyRefreshLayout.setColorSchemeResources(R.color.themeblue);
        mSwipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        adapter = new CompanyRankAdapter(getActivity(), mList);
        adapter.addHeaderView(headView);
        mRecycler.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        Call<String> companyRank = xBService.getCompanyRank(page+"");
        companyRank.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                mSwipyRefreshLayout.setRefreshing(false);
                String s = response.body().toString();
                try {
                    JSONObject jsonObject = parseObject(s);
                    String error = jsonObject.getString("error");
                    String data = jsonObject.getString("data");
                    if ("0".equals(error)) {
                        if (1 == page) {
                            mList.clear();
                        }
                        List<CompanyInfo> list = new ArrayList<CompanyInfo>();
                        list = parseArray(data, CompanyInfo.class);
                        adapter.addData(list);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("++++>", e.toString());
                }
                Log.e("CompanyFragment.mList", mList.size() + "");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mSwipyRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        if (direction == SwipyRefreshLayoutDirection.TOP) {// 下拉
            page = 1;
        } else if (direction == SwipyRefreshLayoutDirection.BOTTOM) {// 上拉
            page++;
            Log.e("++++>page", page + "");
        }
        initData();
    }
}
