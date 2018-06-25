package com.vqsxb.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.vqsxb.R;

/**
 * Created by h on 2017/11/6.
 */

public class DialogUtils {
    private static ProgressDialog dialog;
    @SuppressWarnings("deprecation")
    public static Dialog show(Context context, View view, boolean isBackCannel) {
        Dialog dialog = new Dialog(context, R.style.recommend_isntall_style);
        dialog.setContentView(view);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.height = WindowManager.LayoutParams.FILL_PARENT;
        Window window = dialog.getWindow();
        window.setAttributes(lp);
        window.setGravity(Gravity.BOTTOM);
        // window.setWindowAnimations(R.style.recommend_isntall_animation);
        if (!(context instanceof Activity)) {
            window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG); // 系统中关机对话框就是这个属性
            if (Build.VERSION.SDK_INT > 18) {
                window.setType(WindowManager.LayoutParams.TYPE_TOAST); //
            } else {
                window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT); // 窗口可以获得焦点，响应操作
            }
        }
        if (isBackCannel) {
            dialog.setCancelable(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface arg0, int arg1, KeyEvent arg2) {
                    if (arg2.getAction() == KeyEvent.KEYCODE_BACK) {
                        return true;
                    }
                    return false;
                }
            });
        }
        return dialog;
    }
}
