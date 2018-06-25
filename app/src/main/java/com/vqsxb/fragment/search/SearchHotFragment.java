package com.vqsxb.fragment.search;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vqsxb.R;
import com.vqsxb.activity.SearchActivity;
import com.vqsxb.adapter.SearchHotGideAdapter;
import com.vqsxb.bean.SearchInfo;
import com.vqsxb.fragment.BaseFragment;
import com.vqsxb.widget.RecycItemDecoration;

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

public class SearchHotFragment extends BaseFragment {

    @BindView(R.id.search_hote_tv)
    TextView mSearchHoteTv;
    @BindView(R.id.search_hote_grid)
    RecyclerView mSearchHoteGrid;

    private SearchActivity activity;
    private List<SearchInfo> hotlist = new ArrayList<>();
    SearchHotGideAdapter hotadapter;

    public SearchHotFragment(SearchActivity activity) {
        this.activity = activity;
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_search_hot;
    }

    @Override
    protected void initViews() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 2);
        mSearchHoteGrid.setLayoutManager(gridLayoutManager);
        mSearchHoteGrid.addItemDecoration(new RecycItemDecoration(activity).setBottom(R.dimen.x1));
        hotadapter = new SearchHotGideAdapter(activity, hotlist);
        mSearchHoteGrid.setAdapter(hotadapter);
        hotadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                activity.setHotkey(hotlist.get(position).getTitle());
                activity.search(hotlist.get(position).getTitle());
            }
        });
    }

    @Override
    protected void initData() {
        Call<String> stringCall = xBService.SearchHot();
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body().toString();
                try {
                    JSONObject json = new JSONObject(s);
                    String error = json.getString("error");
                    String data = json.getString("data");
                    if (error.equals("0")) {
                        List<SearchInfo> list = new ArrayList<SearchInfo>();
                        list = parseArray(data, SearchInfo.class);

                        for (int i = 0; i < list.size(); i++) {
                            SearchInfo searchInfo = list.get(i);
                            searchInfo.setPosition(i+1+"");
                        }

                        hotadapter.addData(list);
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
