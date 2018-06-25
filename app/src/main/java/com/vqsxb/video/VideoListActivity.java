package com.vqsxb.video;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vqsxb.R;
import com.vqsxb.activity.BaseActivity;
import com.vqsxb.interf.CommonCallBack;
import com.vqsxb.widget.CustomLoadMoreView;
import com.vqsxb.widget.RecycItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.vqsxb.httputils.ApiService.videoList;

public class VideoListActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_love_count)
    TextView ivLoveCount;
    @BindView(R.id.fl_love_layout)
    FrameLayout flLoveLayout;
    @BindView(R.id.tv_download_count)
    TextView tvDownloadCount;
    @BindView(R.id.fl_download_layout)
    FrameLayout flDownloadLayout;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;

    private int startPage = 0;

    private List<VideoInfo> mList = new ArrayList<VideoInfo>();
    private VideoListAdapter adapter;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_video_list;
    }

    @Override
    protected void initViews() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new RecycItemDecoration(this).setBottom(R.dimen.x1));
        adapter = new VideoListAdapter(this, mList);
        adapter.setEnableLoadMore(true);
        adapter.setLoadMoreView(new CustomLoadMoreView());
        adapter.setOnLoadMoreListener(this, recyclerView);
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initData() {

        videoList(startPage, adapter, mList, new CommonCallBack() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onFailure(String result) {

            }
        });

    }


    @Override
    public void onLoadMoreRequested() {
        startPage++;
        Log.e("++++>startPage", startPage + "");
        videoList(startPage, adapter, mList, new CommonCallBack() {
            @Override
            public void onSuccess(String result) {
                adapter.loadMoreComplete();
            }

            @Override
            public void onFailure(String result) {
                adapter.loadMoreEnd();
            }
        });
    }

    @OnClick({R.id.fl_love_layout, R.id.fl_download_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_love_layout:
                // 收藏
                break;
            case R.id.fl_download_layout:
                // 下载
                Intent intent = new Intent(this, DownLoadActivity.class);
                startActivity(intent);
                break;
        }
    }
}
