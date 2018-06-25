package com.vqsxb.activity;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.vqsxb.R;
import com.vqsxb.common.VqsAPP;
import com.vqsxb.httputils.httpdownprogress.FileCallBack;
import com.vqsxb.httputils.httpdownprogress.FileSubscriber;
import com.vqsxb.utils.DialogUtils;
import com.vqsxb.utils.SDCardUtils;
import com.vqsxb.utils.ToastUtils;
import com.vqsxb.widget.CollapsibleTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.vqsxb.httputils.ApiService.downProgressService;
import static com.vqsxb.httputils.ApiService.updateService;

public class UpdateActivity11111 extends AppCompatActivity {

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

        storageFile = new File(SDCardUtils.getRootDirectory() + "/DemoUpdate/Demo.apk");

        // 获取要更新的信息 判断是否需要更新  并且是否需要权限
        UpdateInfo();

    }

    // 更新
    public Observable<String> UpdateInfo() {
        Call<String> call = updateService.updateIfo("1.1.1", "com.dddk.atf");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {

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
                        View view = View.inflate(UpdateActivity11111.this,R.layout.vqs_updata_layout,null);
                        TextView updata_do = (TextView) view.findViewById(R.id.updata_do);
                        CollapsibleTextView tv_updata = (CollapsibleTextView) view.findViewById(R.id.tv_updata);
                        final Dialog dialog = DialogUtils.show(UpdateActivity11111.this, view, true);
                        tv_updata.setDesc(Html.fromHtml("测试"), TextView.BufferType.NORMAL);
                        updata_do.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                // 点击下载按钮之后dialog消失
                                dialog.dismiss();

                                // 下载APP
                                final String fileName = "app.apk";
                                final String fileStoreDir = Environment.getExternalStorageDirectory().getAbsolutePath();
                                Log.e("++++>", "load: "+fileStoreDir.toString() );
                                final FileCallBack<ResponseBody> callBack = new FileCallBack<ResponseBody>(fileStoreDir,fileName) {
                                    @Override
                                    public void onSuccess(final ResponseBody responseBody){
                                        Message msg = mHandler.obtainMessage();
                                        msg.what = 1;
                                        mHandler.sendMessage(msg);
                                        Toast.makeText(UpdateActivity11111.this, "下载文件成功", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void progress(long progress, long total) {

                                        Log.e("++++>", "progress="+progress);

                                        int percent = (int) ((double)progress/total*100);
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

                                    @Override
                                    public void onStart() {
                                        // 开始下载通知
                                        Log.e("++++>","onStart");
                                        mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
                                        setUpNotification();
                                    }

                                    @Override
                                    public void onCompleted() {
                                        Log.e("++++>","onCompleted");
                                        Message msg = mHandler.obtainMessage();
                                        msg.what = 0;
                                        mHandler.sendMessage(msg);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        //TODO: 对异常的一些处理
                                        Log.e("++++>", "onError="+e.toString());
                                        e.printStackTrace();
                                    }
                                };

                                downLoad(callBack);
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

    private File changeFile(ResponseBody response, String mDestFileDir, String mdestFileName) {
        try{
            InputStream is = response.byteStream();//从服务器得到输入流对象
            long sum = 0;
            File dir = new File(mDestFileDir);
            if (!dir.exists()){
                dir.mkdirs();
            }
            File file = new File(dir, mdestFileName);//根据目录和文件名得到file对象
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buf = new byte[1024*8];
            int len = 0;
            while ((len = is.read(buf)) != -1){
                fos.write(buf, 0, len);
            }
            fos.flush();
            return file;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void downLoad(final FileCallBack<ResponseBody> callBack) {

        downProgressService.download("20170914/com.mod.minion.revolt.money.mod.6.apk")
                .subscribeOn(Schedulers.io())//请求网络 在调度者的io线程
                .observeOn(Schedulers.io()) //指定线程保存文件
                .doOnNext(new Action1<ResponseBody>() {
                    @Override
                    public void call(ResponseBody body) {
                        callBack.saveFile(body);
                        body.close();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread()) //在主线程中更新ui
                .subscribe(new FileSubscriber<ResponseBody>(VqsAPP.getInstance(), callBack));

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

        Log.e("+++>file", file.getPath());
        Log.e("+++>file", file.getAbsolutePath());

        Intent installApkIntent = new Intent();
        installApkIntent.setAction(Intent.ACTION_VIEW);
        installApkIntent.addCategory(Intent.CATEGORY_DEFAULT);
        installApkIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            installApkIntent.setDataAndType(FileProvider.getUriForFile(UpdateActivity11111.this, "com.fileprovider", file), "application/vnd.android.package-archive");
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


}
