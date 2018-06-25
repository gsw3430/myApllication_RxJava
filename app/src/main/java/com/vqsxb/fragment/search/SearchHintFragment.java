package com.vqsxb.fragment.search;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vqsxb.R;
import com.vqsxb.activity.SearchActivity;
import com.vqsxb.adapter.SearchHintAdapter;
import com.vqsxb.bean.SearchInfo;
import com.vqsxb.fragment.BaseFragment;
import com.vqsxb.widget.RecycItemDecoration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.alibaba.fastjson.JSON.parseArray;
import static com.vqsxb.httputils.ApiService.xBService;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class SearchHintFragment extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    private SearchActivity activity;
    // 关键词
    private String keyWord;
    // 页数
    private int page;
    // 集合
    private List<SearchInfo> mList = new ArrayList<SearchInfo>();
    //
    private SearchHintAdapter hintAdapter;

    public SearchHintFragment(SearchActivity activity) {
        this.activity = activity;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_search_hint;
    }

    @Override
    protected void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        hintAdapter = new SearchHintAdapter(activity, mList);
        mRecycler.setLayoutManager(linearLayoutManager);
        mRecycler.addItemDecoration(new RecycItemDecoration(getContext()).setBottom(R.dimen.x1));
        mRecycler.setAdapter(hintAdapter);
        hintAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                activity.search(mList.get(position).getTitle());
            }
        });
    }

    @Override
    protected void initData() {

    }

    public void toSearch(String keyWord) {
        this.keyWord = keyWord;
        // 获取数据
        getData();
    }

    private void getData() {
        Call<String> stringCall = xBService.SearchHint(keyWord);
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body().toString();

                mList.clear();

                try {
                    JSONObject json = new JSONObject(s);
                    String error = json.getString("error");
                    String data = json.getString("data");
                    if (error.equals("0")) {
                        List<SearchInfo> list = new ArrayList<SearchInfo>();
                        list = parseArray(data, SearchInfo.class);

                        hintAdapter.addData(list);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
