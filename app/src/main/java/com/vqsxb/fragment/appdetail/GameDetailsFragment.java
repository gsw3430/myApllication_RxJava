package com.vqsxb.fragment.appdetail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.vqsxb.R;
import com.vqsxb.adapter.GameDetailModuleAdapter;
import com.vqsxb.bean.GameDetailsInfo;
import com.vqsxb.fragment.BaseFragment;
import com.vqsxb.widget.RecycItemDecoration;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.alibaba.fastjson.JSON.parseArray;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class GameDetailsFragment extends BaseFragment {

    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    //详情页的数据
    private String jsondata;
    private List<GameDetailsInfo> mList = new ArrayList<GameDetailsInfo>();
    GameDetailModuleAdapter adapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.fragment_game_details;
    }

    @Override
    protected void initViews() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(manager);
        // mRecyvler.setItemViewCacheSize(0);
        mRecycler.addItemDecoration(new RecycItemDecoration(getActivity()).setBottom(R.dimen.x15));

        adapter = new GameDetailModuleAdapter(getContext(), mList);
        mRecycler.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        if (getArguments() != null) {
            jsondata = getArguments().getString("jsondata");
            try {
                JSONObject json = new JSONObject(jsondata);
                List<GameDetailsInfo> list = new ArrayList<GameDetailsInfo>();
                list = parseArray(json.getString("data"), GameDetailsInfo.class);
                Log.e("GameDetails,list", list.size()+"");

                adapter.addData(list);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static GameDetailsFragment newInstance(String from) {
        GameDetailsFragment fragment = new GameDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("jsondata", from);
        fragment.setArguments(bundle);
        return fragment;
    }
}
