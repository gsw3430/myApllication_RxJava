package com.vqsxb.common;

import android.app.Application;

import com.vqsxb.utils.ToastUtils;

import org.xutils.x;

import static com.vqsxb.httputils.ApiService.init;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class VqsAPP extends Application {

    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        ToastUtils.init(getInstance());
        x.Ext.init(this);

        init();
    }


    public static Application getInstance() {
        return instance;
    }

}
