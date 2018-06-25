package com.vqsxb.video;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.dl7.player.media.IjkPlayerView;
import com.sackcentury.shinebuttonlib.ShineButton;
import com.vqsxb.R;
import com.vqsxb.activity.BaseActivity;
import com.vqsxb.common.Constant;
import com.vqsxb.widget.DialogHelper;
import com.vqsxb.widget.SnackbarUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class VideoActivity extends BaseActivity {

    @BindView(R.id.video_player)
    IjkPlayerView videoPlayer;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.sb_send)
    Button sbSend;
    @BindView(R.id.ll_edit_layout)
    LinearLayout llEditLayout;
    @BindView(R.id.iv_video_share)
    ImageView ivVideoShare;
    @BindView(R.id.iv_video_collect)
    ShineButton ivVideoCollect;
    @BindView(R.id.iv_video_download)
    ImageView ivVideoDownload;
    @BindView(R.id.ll_operate)
    LinearLayout llOperate;

    private VideoInfo info;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_video;
    }

    @Override
    protected void initViews() {

        info = (VideoInfo) getIntent().getParcelableExtra(Constant.VIDEOINFO);

        videoPlayer.init()
                .setTitle(info.getTitle())
                .setVideoSource(null, info.getMp4_url(), null, null, null);
        Glide.with(this).load(info.getCover()).fitCenter().into(videoPlayer.mPlayerThumb);

        // 播放视屏
        // videoPlayer.start();

        // 初始化收藏按钮
        ivVideoCollect.init(this);
        ivVideoCollect.setShapeResource(R.mipmap.ic_video_collect);

        llOperate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.sb_send, R.id.iv_video_share, R.id.iv_video_collect, R.id.iv_video_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_send:

                break;
            case R.id.iv_video_share:

                break;
            case R.id.iv_video_collect:

                break;
            case R.id.iv_video_download:
                if (view.isSelected()) { // 是否被选中
                    DialogHelper.checkDialog(this, info);
                } else {
                    DialogHelper.downloadDialog(this, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DownloaderWrapper.start(info);
                            ivVideoDownload.setSelected(true);
                            SnackbarUtils.showDownloadSnackbar(VideoActivity.this, "任务正在下载", true);
                        }
                    });
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoPlayer.onDestroy();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        videoPlayer.configurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (videoPlayer.handleVolumeKey(keyCode)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (videoPlayer.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
