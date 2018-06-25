package com.vqsxb.adapter;

import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vqsxb.R;
import com.vqsxb.bean.OnlineInfo;
import com.vqsxb.viewholder.OnlineRankHolder;

import java.util.List;

/**
 * 评论列表
 * Created by h on 2017/10/23.
 */

public class OnlineRankAdapter extends BaseQuickAdapter<OnlineInfo, OnlineRankHolder> {

    private Activity activity;
    private List<OnlineInfo> info;

    public OnlineRankAdapter(Activity activity, List<OnlineInfo> infos) {
        super(R.layout.item_online_rank_layout, infos);
        this.activity = activity;
        this.info = infos;

    }
    @Override
    protected void convert(OnlineRankHolder holder, OnlineInfo item) {
        holder.setIsRecyclable(false);

        for (int i = 0; i < info.size(); i++) {
            OnlineInfo onlineInfo = info.get(i);
            onlineInfo.setPosition(i+1);
        }

        holder.update(activity, item);
    }
}
