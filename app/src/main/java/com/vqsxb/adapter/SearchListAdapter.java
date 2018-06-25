package com.vqsxb.adapter;

import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vqsxb.R;
import com.vqsxb.bean.OnlineInfo;
import com.vqsxb.viewholder.SearchListHolder;

import java.util.List;

/**
 * Created by Administrator on 2017/12/4 0004.
 */

public class SearchListAdapter extends BaseQuickAdapter<OnlineInfo, SearchListHolder> {
    private Activity activity;
    private List<OnlineInfo> info;

    public SearchListAdapter(Activity activity, List<OnlineInfo> infos) {
        super(R.layout.item_search_list_layout, infos);
        this.activity = activity;
        this.info = infos;

    }
    @Override
    protected void convert(SearchListHolder holder, OnlineInfo item) {
        holder.setIsRecyclable(false);
        holder.update(activity, item);
    }
}
