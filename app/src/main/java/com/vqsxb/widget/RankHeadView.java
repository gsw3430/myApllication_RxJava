package com.vqsxb.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vqsxb.R;
import com.vqsxb.interf.RankHeadCallBack;
import com.vqsxb.utils.ColorUtil;
import com.vqsxb.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/24.
 */

public class RankHeadView extends LinearLayout {
    private TextView tab_top1,tab_top2,tab_top3;
    private TextView ranklist_content1,ranklist_content2,ranklist_content3,ranklist_content4;
    private LinearLayout ranklist_content_ll;
    private int category = 1;//1=网游, 2=单机, 3=厂商排行
    private int type = 1;//1=下载榜, 2=新游榜, 3=预约榜, 4=畅销榜
    private Context context;
    private View view;
    private List<TextView> tops = new ArrayList<>();
    private List<TextView> contents = new ArrayList<>();
    public void init(int category,int type){
        this.category = category;
        this.type = type;
        if (category == 3){
            setTopClick(tab_top3);
            ranklist_content_ll.setVisibility(View.GONE);
        }else{
            setTopClick(tops.get(category-1));
            setContentClick(contents.get(type-1));
        }
    }
    public void setClickListener(final RankHeadCallBack listener){
        for (int i = 0; i < tops.size(); i++) {
            final int j = i;
            tops.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(j+1,1);
                }
            });
        }
        for (int i = 0; i < contents.size(); i++) {
            final int j = i;
            contents.get(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(category,j+1);
                }
            });
        }
    }
    private void setTopClick(TextView tv){
        tv.setBackgroundResource(R.drawable.tab_top_select);
        tv.setTextColor(ColorUtil.getColor(context,R.color.white));
    }
    private void setContentClick(TextView tv){
        tv.setTextColor(ColorUtil.getColor(context,R.color.colorPrimary));
    }

    public RankHeadView(Context context) {
        super(context);
        initView(context);
    }

    public RankHeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RankHeadView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        this.view = LayoutInflater.from(context).inflate(R.layout.rank_head_view, this);
        tab_top1 = ViewUtil.find(view,R.id.tab_top1);
        tab_top2 = ViewUtil.find(view,R.id.tab_top2);
        tab_top3 = ViewUtil.find(view,R.id.tab_top3);
        ranklist_content1 = ViewUtil.find(view,R.id.ranklist_content1);
        ranklist_content2 = ViewUtil.find(view,R.id.ranklist_content2);
        ranklist_content3 = ViewUtil.find(view,R.id.ranklist_content3);
        ranklist_content4 = ViewUtil.find(view,R.id.ranklist_content4);
        ranklist_content_ll = ViewUtil.find(view,R.id.ranklist_content_ll);
        tops.add(tab_top1);
        tops.add(tab_top2);
        tops.add(tab_top3);
        contents.add(ranklist_content1);
        contents.add(ranklist_content2);
        contents.add(ranklist_content3);
        contents.add(ranklist_content4);
    }



}
