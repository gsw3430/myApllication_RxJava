package com.vqsxb.video;

import android.app.Activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vqsxb.R;

import java.util.List;

/**
 * Created by Administrator on 2017/12/4 0004.
 */

public class VideoListAdapter extends BaseQuickAdapter<VideoInfo, VideoListHolder> {
    private Activity activity;
    private List<VideoInfo> info;

    public VideoListAdapter(Activity activity, List<VideoInfo> infos) {
        super(R.layout.item_video_list, infos);
        this.activity = activity;
        this.info = infos;

    }
    @Override
    protected void convert(VideoListHolder holder, VideoInfo item) {
        holder.setIsRecyclable(false);
        holder.update(activity, item);
    }
}
