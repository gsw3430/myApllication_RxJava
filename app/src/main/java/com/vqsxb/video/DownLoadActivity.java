package com.vqsxb.video;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.vqsxb.R;
import com.vqsxb.activity.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.vqsxb.common.Constant.INDEX_KEY;
// 下载中心
public class DownLoadActivity extends BaseActivity {

    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    @BindView(R.id.mine_content_tab)
    TabLayout mineContentTab;
    @BindView(R.id.mine_content_viewpager)
    ViewPager mineContentViewpager;
    @BindView(R.id.tv_back)
    TextView tvBack;

    public static void launch(Context context, int index) {
        Intent intent = new Intent(context, DownLoadActivity.class);
        intent.putExtra(INDEX_KEY, index);
        context.startActivity(intent);
       // ((Activity)context).overridePendingTransition(R.anim.zoom_in_entry, R.anim.hold);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_down_load;
    }

    @Override
    protected void initViews() {
        tvBack.setText("下载管理");
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.tv_back)
    public void onViewClicked() {
        finish();
    }
}
