package com.vqsxb.activity;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.cm.retrofit2.converter.file.body.ProgressResponseListener;
import com.vqsxb.R;
import com.vqsxb.httputils.progress.DownloadService;
import com.vqsxb.httputils.progress.ServiceGenerator;
import com.vqsxb.utils.DialogUtils;
import com.vqsxb.utils.SDCardUtils;
import com.vqsxb.utils.ToastUtils;
import com.vqsxb.widget.CollapsibleTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;

import static com.vqsxb.httputils.ApiService.updateService;

// 更新测试
public class UpdateActivity extends AppCompatActivity implements ProgressResponseListener {

    String url = "http://download.appmod.net/20170914/com.mod.minion.revolt.money.mod.6.apk";
    private Notification mNotification = null;
    private NotificationManager mNotificationManager;
    private String mTitle = "正在下载";
    private static final int NOTIFY_ID = 0;

    private static final int REQUEST_CODE_PERMISSION_SD = 100;
    // 下载路径
    private File storageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        // 获取要更新的信息 判断是否需要更新  并且是否需要权限
        UpdateInfo();


    }

    // 更新
    public Observable<String> UpdateInfo() {
        Call<String> call = updateService.updateIfo("1.1.1", "com.dddk.atf");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String s = response.body().toString();
                Log.e("++++>UpdateActivity", s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject info = jsonObject.getJSONObject("info");
                    int type = info.getInt("type");

                    type = 1;

                    if (0 == type) {
                        ToastUtils.showToast("已经是最新版本");
                    }else if (1 == type) {
                        ToastUtils.showToast("提示更新");

                        // 弹出提示框， 并下载
                        View view = View.inflate(UpdateActivity.this,R.layout.vqs_updata_layout,null);
                        TextView updata_do = (TextView) view.findViewById(R.id.updata_do);
                        CollapsibleTextView tv_updata = (CollapsibleTextView) view.findViewById(R.id.tv_updata);
                        final Dialog dialog = DialogUtils.show(UpdateActivity.this, view, true);
                        tv_updata.setDesc(Html.fromHtml("测试"), TextView.BufferType.NORMAL);
                        updata_do.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                // 点击下载按钮之后dialog消失
                                dialog.dismiss();

                                // 下载APP
                                storageFile = new File(SDCardUtils.getRootDirectory() + "/DemoUpdate/Demo.apk");

                                String savePath = getExternalFilesDir(null)+ File.separator+"Demo.apk";

                                DownloadService downloadService = ServiceGenerator.createResponseService(DownloadService.class, UpdateActivity.this);
                                Call<File> call = downloadService.download(url,savePath);

                                // 开始下载通知
                                mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
                                setUpNotification();

                                call.enqueue(new Callback<File>() {
                                    @Override
                                    public void onResponse(Call<File> call, Response<File> response) {

                                        // 下载成功
                                        if(response.isSuccessful() && response.body() != null){
                                            //Log.e("onResponse","file path:"+response.body().getPath());
                                            String path = response.body().getPath();
                                            installApps(new File(path));
                                        }

                                       // mProgressBar.setVisibility(View.GONE);
                                        Message msg = mHandler.obtainMessage();
                                        msg.what = 0;
                                        mHandler.sendMessage(msg);
                                    }

                                    @Override
                                    public void onFailure(Call<File> call, Throwable t) {
                                        //mProgressBar.setVisibility(View.GONE);

                                        Message msg = mHandler.obtainMessage();
                                        msg.what = 0;
                                        mHandler.sendMessage(msg);
                                    }
                                });


                                // downLoad(callBack);
                            }
                        });

                        dialog.show();

                    }else if (2 == type) {
                        ToastUtils.showToast("强制更新");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return null;
    }

    private void setUpNotification() {
        int icon = R.mipmap.login_default_icon;
        CharSequence tickerText = "准备下载";
        long when = System.currentTimeMillis();
        mNotification = new Notification(icon, tickerText, when);
        // 放置在"正在运行"栏目中
        mNotification.flags = Notification.FLAG_ONGOING_EVENT;
        RemoteViews contentView = new RemoteViews(getPackageName(),
                R.layout.download_notification_show);
        contentView.setTextViewText(R.id.tv_download_state, mTitle);
        // 指定个性化视图
        mNotification.contentView = contentView;
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // 指定内容意图
        mNotification.contentIntent = contentIntent;
        mNotificationManager.notify(NOTIFY_ID, mNotification);
    }

    // 安装
    public void installApps(File file) {
        Intent installApkIntent = new Intent();
        installApkIntent.setAction(Intent.ACTION_VIEW);
        installApkIntent.addCategory(Intent.CATEGORY_DEFAULT);
        installApkIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            installApkIntent.setDataAndType(FileProvider.getUriForFile(UpdateActivity.this, "com.fileprovider", file), "application/vnd.android.package-archive");
            installApkIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            installApkIntent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }

        if (getPackageManager().queryIntentActivities(installApkIntent, 0).size() > 0) {
            startActivity(installApkIntent);
        }
    }

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    // 下载完毕
                    mNotificationManager.cancel(NOTIFY_ID);
                    mNotificationManager.cancelAll();
                    break;
                case 2:
                    // 取消通知
                    mNotificationManager.cancel(NOTIFY_ID);
                    break;
                case 1:
                    int rate = msg.arg1;
                    if (rate < 100) {
                        RemoteViews contentview = mNotification.contentView;
                        contentview.setTextViewText(R.id.tv_download_state, mTitle + "(" + rate + "%" + ")");
                        contentview.setProgressBar(R.id.pb_download, 100, rate, false);

                        mNotificationManager.notify(NOTIFY_ID, mNotification);
                    }else {
                        mNotificationManager.cancelAll();
                    }
                    break;
            }
        }
    };

    @Override
    public void onResponseProgress(long bytesRead, long contentLength, boolean done) {
        int percent = (int)(1.0f*bytesRead/contentLength*100);
        if (percent < 100) {
        Message msg = mHandler.obtainMessage();
        msg.what = 1;
        msg.arg1 = percent;
        mHandler.sendMessage(msg);
        }else {
        Message msg = mHandler.obtainMessage();
        msg.what = 0;
        mHandler.sendMessage(msg);
        }
    }
}
