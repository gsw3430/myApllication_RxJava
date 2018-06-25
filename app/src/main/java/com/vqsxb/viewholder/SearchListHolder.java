package com.vqsxb.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vqsxb.R;
import com.vqsxb.activity.DetailsActivity;
import com.vqsxb.bean.OnlineInfo;
import com.vqsxb.utils.OtherUtil;
import com.vqsxb.utils.ViewUtil;
import com.vqsxb.widget.FlowLayout;
import com.vqsxb.widget.MyRatingBar;

import java.util.List;

/**
 * Created by Administrator on 2017/11/28 0028.
 */

public class SearchListHolder extends BaseViewHolder {

    private ImageView rank_manager_item_icon;
    private TextView rank_manager_title,rank_manager_score,rank_manager_item_number, rank_ruanjianzhuangtai_name;
    private FlowLayout mFlowLayout;
    private MyRatingBar rank_manager_star;
    private View itemView;

    public SearchListHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        initView();
    }

    private void initView() {
        rank_manager_item_icon = ViewUtil.find(itemView, R.id.rank_manager_item_icon);
        rank_manager_title = ViewUtil.find(itemView, R.id.rank_manager_title);
        mFlowLayout = ViewUtil.find(itemView, R.id.content_app_head_tag_all);
        rank_manager_star = ViewUtil.find(itemView, R.id.rank_manager_star);
        rank_manager_score = ViewUtil.find(itemView, R.id.rank_manager_score);
        rank_manager_item_number = ViewUtil.find(itemView, R.id.rank_manager_item_number);
        rank_ruanjianzhuangtai_name = ViewUtil.find(itemView, R.id.rank_ruanjianzhuangtai_name);
    }

    public void update(final Activity activity, final OnlineInfo info) {
        rank_manager_title.setText(info.getTitle());
        Glide.with(activity).load(info.getIcon()).asBitmap().into(rank_manager_item_icon);
        rank_manager_star.setStar(info.getScore());
        rank_ruanjianzhuangtai_name.setText(info.getRuanjianzhuangtai_name());
        rank_manager_score.setText(info.getScore()+"分");

        /*rank_manager_item_number.setText(info.getPosition()+"");
        if (info.getPosition()>3){
            rank_manager_item_number.setTextColor(ColorUtil.getColor(activity,R.color.text_tab_gray));
        }else{
            rank_manager_item_number.setTextColor(ColorUtil.getColor(activity,R.color.text_orange));
        }*/

        mFlowLayout.removeAllViews();
        List<OnlineInfo.TagBean> tags = info.getTag();
        if (OtherUtil.isListNotEmpty(tags)) {
            while (tags.size()>3){
                tags.remove(tags.size()-1);
            }
            for (int i = 0; i < tags.size(); i++) {
                final OnlineInfo.TagBean tag = tags.get(i);
                TextView tagView = (TextView) LayoutInflater.from(activity).inflate(R.layout.tag_appitem_layout, mFlowLayout, false);
                tagView.setText(tag.getName());
                tagView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // ActivityUtil.gotoListGameMoreActivity(activity,tag.getName(),tag.getId());
                    }
                });
                mFlowLayout.addView(tagView);
            }
        }
        //initHolder(activity,info);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetailsActivity.class);
                intent.putExtra("appID", info.getAppID());
                Log.e("OnlineRankHolder.appID", info.getAppID());
                activity.startActivity(intent);
            }
        });

    }
   /* private void initHolder(Activity activity, final RankInfo info){
        initBaseHolder(activity, info, new StateCallBack() {
            @Override
            public void getState(DownloadState state) {
                downProgressButton.setState(state, DownButtonState.valueOfString(info.getRuanjianzhuangtai()));
            }

            @Override
            public void progress(long total, long current) {
                downProgressButton.setProgress((int) (current*100/total));
            }
        });
        downProgressButton.setOnClick(activity,this,info);
    }*/
}
