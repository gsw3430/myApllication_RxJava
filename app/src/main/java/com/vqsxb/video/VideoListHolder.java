package com.vqsxb.video;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vqsxb.R;
import com.vqsxb.common.Constant;
import com.vqsxb.utils.ViewUtil;
import com.vqsxb.viewholder.BaseModuleHolder;

/**
 * Created by Administrator on 2017/11/28 0028.
 */

public class VideoListHolder extends BaseModuleHolder {

    private Activity activity;
    private ImageView iv_photo;
    private TextView tv_title;
    private View itemView;

    public VideoListHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        iv_photo = ViewUtil.find(itemView, R.id.iv_photo);
        tv_title = ViewUtil.find(itemView, R.id.tv_title);
    }

    @Override
    public ImageView getImageView() {
        return null;
    }

    public void update(final Activity activity, final VideoInfo info){
        this.activity = activity;
        tv_title.setText(info.getTitle());
        Glide.with(activity).load(info.getCover()).asBitmap().into(iv_photo);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, VideoActivity.class);
                intent.putExtra(Constant.VIDEOINFO, info);
                activity.startActivity(intent);
            }
        });
    }
}
