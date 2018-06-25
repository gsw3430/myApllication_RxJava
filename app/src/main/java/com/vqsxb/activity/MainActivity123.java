package com.vqsxb.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.vqsxb.R;
import com.vqsxb.interf.OnTabReselectListener;
import com.vqsxb.utils.ToastUtils;
import com.vqsxb.widget.MyFragmentTabHost;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity123 extends AppCompatActivity implements TabHost.OnTabChangeListener,
        View.OnTouchListener, View.OnClickListener {


    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.realtabcontent)
    FrameLayout mRealtabcontent;
    @BindView(R.id.tab_host)
    MyFragmentTabHost mTabHost;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    // 标题
    private TextView tv_title;
    private String[] mTitles;
    // 头像
    private ImageView iv_icon;
    // 搜索
    private ImageView iv_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main123);

        ButterKnife.bind(this);
        initView();

    }

    private void initView() {
        mTitles = getResources().getStringArray(R.array.main_titles_arrays);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        if (android.os.Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }

        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_icon = (ImageView) findViewById(R.id.iv_icon);
        iv_search = (ImageView) findViewById(R.id.iv_search);
        iv_icon.setOnClickListener(this);
        iv_search.setOnClickListener(this);

        initTabs();
        mTabHost.setCurrentTab(0);
        mTabHost.setOnTabChangedListener(this);


    }

    private void initTabs() {
        MainTab[] tabs = MainTab.values();

        for (int i = 0; i < tabs.length; i++) {
            MainTab mainTab = tabs[i];
            TabHost.TabSpec tab = mTabHost.newTabSpec(getString(mainTab.getResName()) + this.toString());
            View indicator = View.inflate(this, R.layout.tab_indicator, null);
            TextView title = (TextView) indicator.findViewById(R.id.tab_title);
            ImageView icon = (ImageView) indicator.findViewById(R.id.iv_icon);

            Drawable drawable = this.getResources().getDrawable(mainTab.getResIcon());
            icon.setImageDrawable(drawable);
            title.setText(getString(mainTab.getResName()));
            tab.setIndicator(indicator);
            mTabHost.addTab(tab, mainTab.getClz(), null);
            mTabHost.getTabWidget().getChildAt(i).setOnTouchListener(this);
        }
    }

    @Override
    public void onTabChanged(String tabId) {
        final int size = mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View v = mTabHost.getTabWidget().getChildAt(i);
            if (i == mTabHost.getCurrentTab()) {
                v.setSelected(true);
                tv_title.setText(mTitles[i]);
            } else {
                v.setSelected(false);
            }
        }

        supportInvalidateOptionsMenu();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        super.onTouchEvent(event);
        boolean consumed = false;
        // use getTabHost().getCurrentTabView to decide if the current tab is
        // touched again
        if (event.getAction() == MotionEvent.ACTION_DOWN
                && view.equals(mTabHost.getCurrentTabView())) {
            // use getTabHost().getCurrentView() to get a handle to the view
            // which is displayed in the tab - and to get this views context
            Fragment currentFragment = getCurrentFragment();
            if (currentFragment != null
                    && currentFragment instanceof OnTabReselectListener) {
                OnTabReselectListener listener = (OnTabReselectListener) currentFragment;
                listener.onTabReselect();
                consumed = true;
            }
        }
        return consumed;
    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag(
                mTabHost.getCurrentTabTag());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_icon:
                ToastUtils.showToast("个人中心");
                break;

            case R.id.iv_search:
                ToastUtils.showToast("搜索");
                break;
        }
    }
}
