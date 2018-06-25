package com.vqsxb.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vqsxb.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    //缓存Fragment view
    private View mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), null);
            ButterKnife.bind(this, mRootView);
            initViews();
            initData();
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    /**
     * 绑定布局文件
     * @return  布局文件ID
     */
    protected abstract int attachLayoutRes();


    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        ((BaseActivity)getActivity()).initToolBar(toolbar, homeAsUpEnabled, title);
    }
}
