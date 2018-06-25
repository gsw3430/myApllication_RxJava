package com.vqsxb.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vqsxb.R;
import com.vqsxb.bean.CompanyInfo;
import com.vqsxb.viewholder.CompanyRankHolder;

import java.util.List;

/**
 * 评论列表
 * Created by h on 2017/10/23.
 */

public class CompanyRankAdapter extends BaseQuickAdapter<CompanyInfo, CompanyRankHolder> {
    private Activity activity;
    private List<CompanyInfo> data;

    public CompanyRankAdapter(Activity activity, @Nullable List<CompanyInfo> data) {
        super(R.layout.item_company_rank_layout, data);
        this.activity = activity;
        this.data = data;
    }


    @Override
    protected void convert(CompanyRankHolder holder, CompanyInfo item) {
        holder.setIsRecyclable(false);
        holder.update(activity, item);
    }
}
