package com.vqsxb.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.vqsxb.R;
import com.vqsxb.activity.MainActivity;
import com.vqsxb.adapter.BaseModuleAdapter;
import com.vqsxb.bean.RecommendInfo;
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

public class RecommendFragment extends BaseFragment implements SwipyRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycler)
    RecyclerView mRecyvler;
    @BindView(R.id.swipyRefreshLayout)
    SwipyRefreshLayout mSwipyRefreshLayout;

    private List<RecommendInfo> mList = new ArrayList<RecommendInfo>();
    private int page = 1;
    private int drop = 0;
    BaseModuleAdapter baseModuleAdapter;
    MainActivity mainActivity;

    @Override
    protected int attachLayoutRes() {
        mainActivity = (MainActivity) getActivity();
        return R.layout.fragment_recomment;
    }

    @Override
    protected void initViews() {
        mSwipyRefreshLayout.setColorSchemeResources(R.color.themeblue);// 设置加载进度条颜色
        mSwipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.BOTH);
        mSwipyRefreshLayout.setOnRefreshListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyvler.setLayoutManager(manager);
       // mRecyvler.setItemViewCacheSize(0);
        mRecyvler.addItemDecoration(new RecycItemDecoration(getActivity()).setBottom(R.dimen.x15));
        baseModuleAdapter = new BaseModuleAdapter(getActivity(), mList);
        mRecyvler.setAdapter(baseModuleAdapter);

        mRecyvler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int mScrollThreshold;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isSignificantDelta = Math.abs(dy) > mScrollThreshold;
                if (isSignificantDelta) {
                    if (dy > 0) {
                        onScrollUp();
                    } else {
                        onScrollDown();
                    }
                }
            }
        });

        getData();

    }

    /**
     * 下滑监听
     */
    private void onScrollDown() {
        mainActivity.test("1");
    }

    /**
     * 上滑监听
     */
    private void onScrollUp() {
//上滑时要执行的代码
        mainActivity.test("2");
    }

    private void getData() {
        Call<String> call = xBService.getRecommentData(page + "", drop + "");
        call.enqueue(new Callback<String>() {
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
                        List<RecommendInfo> list = new ArrayList<RecommendInfo>();
                        list = parseArray(data, RecommendInfo.class);
                        baseModuleAdapter.addData(list);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("++++>", e.toString());
                }
                Log.e("++++>mList", mList.size() + "");
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mSwipyRefreshLayout.setRefreshing(false);
            }
        });
    }


    @Override
    protected void initData() {

    }

    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        if (direction == SwipyRefreshLayoutDirection.TOP) {// 下拉
            page = 1;
            drop++;
        } else if (direction == SwipyRefreshLayoutDirection.BOTTOM) {// 上拉
            page++;
            Log.e("++++>page", page + "");
        }
        getData();
    }
}
