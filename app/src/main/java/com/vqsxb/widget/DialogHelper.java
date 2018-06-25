package com.vqsxb.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.dl7.player.utils.SDCardUtils;
import com.vqsxb.video.VideoInfo;
import com.vqsxb.utils.StringUtils;
import com.vqsxb.video.DownLoadActivity;
import com.vqsxb.video.DownloadStatus;

/**
 * Created by Rukey7 on 2016/12/12.
 */

public final class DialogHelper {

    private DialogHelper() {
        throw new AssertionError();
    }

    /**
     * 下载对话框
     * @param context
     * @param listener
     */
    public static void downloadDialog(Context context, DialogInterface.OnClickListener listener) {
        String title = "剩余容量(" + StringUtils.convertStorageNoB(SDCardUtils.getFreeSpaceBytes()) + ")";
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage("下载需要大量流量，在非wifi下请慎重，是否下载?")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", listener);
        builder.create().show();
    }

    /**
     * 查阅对话框
     * @param context
     * @param data
     */
    public static void checkDialog(final Context context, final VideoInfo data) {
        String msg;
        if (data.getDownloadStatus() == DownloadStatus.COMPLETE) {
            msg = "任务已缓存，点击查看";
        } else {
            msg = "任务正在缓存，点击查看";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示")
                .setMessage(msg)
                .setNegativeButton("取消", null)
                .setPositiveButton("查看任务", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (data.getDownloadStatus() == DownloadStatus.COMPLETE) {
                            DownLoadActivity.launch(context, 0);
                        } else {
                            DownLoadActivity.launch(context, 1);
                        }
                    }
                });
        builder.create().show();
    }

    /**
     * 删除
     * @param context
     * @param listener
     */
    public static void deleteDialog(Context context, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("是否删除?").setPositiveButton("确定", listener).setNegativeButton("取消", null);
        builder.create().show();
    }
}
