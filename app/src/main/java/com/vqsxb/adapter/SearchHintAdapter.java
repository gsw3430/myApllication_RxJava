package com.vqsxb.adapter;

import android.app.Activity;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vqsxb.R;
import com.vqsxb.bean.SearchInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10.
 * des ：搜索热门搜索Grid adapter  --Yalun
 */

public class SearchHintAdapter extends BaseQuickAdapter<SearchInfo, BaseViewHolder> {
    private Activity activity;
    List<SearchInfo> list;
    public SearchHintAdapter(Activity activity, List<SearchInfo> list) {
        super(R.layout.search_hot_item,list);
        this.activity = activity;
        this.list = list;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchInfo item) {

        ((TextView) helper.getView(R.id.tv_content)).setText(item.getTitle());
        ((TextView) helper.getView(R.id.tv_rank)).setText(item.getPosition());
    }
}
