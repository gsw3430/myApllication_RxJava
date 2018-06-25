package com.vqsxb.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vqsxb.R;
import com.vqsxb.utils.ViewUtil;

/**
 * Created by Administrator on 2017/11/17.
 */

public class EmptyView extends RelativeLayout {
    private View view;
    private Context context;
    private TextView loadingError,nodate_text;
    private LinearLayout loadingNodate,nodate_wdj,nodate_baidu;

    public EmptyView(Context context) {
        super(context);
        initView(context);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    public void setNodataText(String text){
        nodate_text.setText(text);
    }
    public void showThirdButton(){
        nodate_wdj.setVisibility(View.VISIBLE);
        nodate_baidu.setVisibility(View.VISIBLE);
    }
    public void setWdjClick(OnClickListener listener){
        nodate_wdj.setOnClickListener(listener);
    }
    public void setBaiduClick(OnClickListener listener){
        nodate_baidu.setOnClickListener(listener);
    }


    private void initView(Context context) {
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.loading_error_layout, this);
        loadingError = ViewUtil.find(view, R.id.loading_error);
        loadingNodate = ViewUtil.find(view, R.id.loading_nodate);
        nodate_text = ViewUtil.find(view, R.id.nodate_text);
        nodate_wdj = ViewUtil.find(view, R.id.nodate_wdj);
        nodate_baidu = ViewUtil.find(view, R.id.nodate_baidu);
    }
    /**
     * 显示加载错误的布局
     */
    public void showError(){
        loadingError.setVisibility(View.VISIBLE);
        loadingNodate.setVisibility(View.INVISIBLE);
    }

    /**
     * 显示没有数据的布局
     */
    public void showNodate(){
        loadingError.setVisibility(View.INVISIBLE);
        loadingNodate.setVisibility(View.VISIBLE);
    }

    /**
     * 显示空白布局
     */
    public void showNone(){
        loadingError.setVisibility(View.INVISIBLE);
        loadingNodate.setVisibility(View.INVISIBLE);
    }
}
