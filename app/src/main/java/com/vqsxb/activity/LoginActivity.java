package com.vqsxb.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vqsxb.R;
import com.vqsxb.bean.find.FindModel;
import com.vqsxb.utils.ImageUtils;
import com.vqsxb.video.VideoListActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static com.vqsxb.httputils.ApiService.getFindData;
import static com.vqsxb.httputils.ApiService.getUserdata;
import static com.vqsxb.httputils.ApiService.modifyInformation;
// 测试
public class LoginActivity extends AppCompatActivity {

    EditText et_email;
    EditText et_password;

    List<FindModel.DataBeanX> findData = new ArrayList<>();
    // 头像保存的路径
    String path;

    // 拍照或者从相册中选择
    Button btn_avatar;
    // 修改头像的dialog
    private Dialog dialogiphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);

        Button btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        findData = getFindData("1");
        Log.e("+++>", "findData=" + findData.size());

        btn_avatar = (Button) findViewById(R.id.btn_avatar);
        btn_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 更换头像
                showTypeDialog(LoginActivity.this);
            }
        });

        findViewById(R.id.btn_modify_information).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 修改资料
                Log.e("++++>path", path);
                modifyInformation(path);
            }
        });

        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, UpdateActivity.class));
            }
        });

        findViewById(R.id.btn_update22).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, UpdateActivity11111.class));
            }
        });

        findViewById(R.id.btn_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, VideoListActivity.class));
            }
        });

        final ImageView btn_click = (ImageView) findViewById(R.id.btn_click);
        btn_click.setImageResource(R.drawable.down_button_bg);

        findViewById(R.id.btn_zhibo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PlayerActivity.class));
            }
        });

    }

    private void showTypeDialog(Context mContext) {
        dialogiphone = new Dialog(mContext, R.style.recommend_isntall_style);
        View view = setUpView2(mContext);
        dialogiphone.setContentView(view);
        dialogiphone.show();
    }

    View setUpView2(final Context context) {
        View view;
        view = LayoutInflater.from(context).inflate(R.layout.phone_dialog, null);
        TextView tv_select_gallery = (TextView) view.findViewById(R.id.tv_select_gallery);
        TextView tv_select_camera = (TextView) view.findViewById(R.id.tv_select_camera);
        TextView quxiao = (TextView) view.findViewById(R.id.tv_quxiao);
        tv_select_gallery.setOnClickListener(new View.OnClickListener() {// 在相册中选取
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, 1);
                dialogiphone.dismiss();
            }
        });
        tv_select_camera.setOnClickListener(new View.OnClickListener() {// 调用照相机
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "screenshot.jpg")));
                startActivityForResult(intent2, 2);// 采用ForResult打开
                dialogiphone.dismiss();
            }
        });

        quxiao.setOnClickListener(new View.OnClickListener() {// 取消
            @Override
            public void onClick(View v) {
                dialogiphone.dismiss();
            }
        });

        return view;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)

    {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        cropPhoto(data.getData());// 裁剪图片
                    }
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory() + "/screenshot.jpg");
                    cropPhoto(Uri.fromFile(temp));// 裁剪图片
                }
                break;
            case 3:
                if (data != null) {
                    try {
                        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                        bitmap = ImageUtils.cQuality(bitmap);
                        //iv_icon.setImageBitmap(bitmap);
                        path = SaveFile(bitmap);
                        // uploadHeadPic(bitmap);
                    } catch (Exception e) {
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                           // iv_icon.setImageBitmap(bitmap);
                            path = SaveFile(bitmap);
                            // uploadHeadPic(bitmap);
                        } catch (Throwable e1) {
                        }
                    }
                }
                break;

        }
    }

    /**
     * 调用系统的裁剪功能
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //开启裁剪功能
        intent.putExtra("crop", "true");
        //设定宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //设定裁剪图片宽高
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 600);
        intent.putExtra("scale", true);
        //要求返回数据
        intent.putExtra("outputFormat", "JPEG");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    /**
     * 保存文件
     */
    public static String SaveFile(Bitmap bitmap) {
        String name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
        // 获取相机返回的数据，并转换为图片格式
        FileOutputStream fout = null;
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/vqsgjb/img/");
        if (!file.exists()) {
            file.mkdirs();
        }
        String filepath = file.getPath() + "/" + name;
        try {
            fout = new FileOutputStream(filepath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fout);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fout.flush();
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filepath;
    }

    private void Login() {

        Log.e("+++>", "findData=" + findData.size());

        getUserdata(et_email.getText().toString(), et_password.getText().toString());

    }
}
