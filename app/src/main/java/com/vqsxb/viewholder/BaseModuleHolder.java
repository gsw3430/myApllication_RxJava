package com.vqsxb.viewholder;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by Administrator on 2017/11/28 0028.
 */

public abstract class BaseModuleHolder extends BaseViewHolder {
    public BaseModuleHolder(View itemView) {
        super(itemView);
        itemView.setTag(this);
    }
    /**
     * 返回需要转场动画的ImageView
     */
    public abstract ImageView getImageView();
}