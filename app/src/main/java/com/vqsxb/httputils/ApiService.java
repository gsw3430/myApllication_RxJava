package com.vqsxb.httputils;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.vqsxb.adapter.OnlineRankAdapter;
import com.vqsxb.adapter.SearchListAdapter;
import com.vqsxb.bean.OnlineInfo;
import com.vqsxb.bean.find.FindModel;
import com.vqsxb.bean.user.LoginModel;
import com.vqsxb.bean.user.UserData;
import com.vqsxb.httputils.httpdownprogress.ProgressInterceptor;
import com.vqsxb.interf.CommonCallBack;
import com.vqsxb.utils.OtherUtil;
import com.vqsxb.utils.ToastUtils;
import com.vqsxb.video.VideoInfo;
import com.vqsxb.video.VideoListAdapter;

import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;

import static com.alibaba.fastjson.JSON.parseArray;
import static com.alibaba.fastjson.JSON.parseObject;

/**
 * Created by Administrator on 2017/11/15 0015.
 */

public class ApiService {

    //设缓存有效期为1天
    static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //(假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)
    static final String CACHE_CONTROL_NETWORK = "Cache-Control: public, max-age=3600";
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;



    private static final String url = "https://api.appmod.net/";
    private static Api sUserService;

    private static final String xburl = "http://120.24.254.190:83/";
    public static Api xBService;

    // 更新
    public static Api updateService;
    // 下载进度
    public static Api downProgressService;

    // 视屏列表
    public static Api vedioListService;

    public static void init() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        sUserService = retrofit.create(Api.class);

        Retrofit retrofitXb = new Retrofit.Builder()
                .baseUrl(xburl)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        xBService = retrofitXb.create(Api.class);

        // 更新
        Retrofit retrofitUpdate = new Retrofit.Builder()
                .baseUrl("http://lp.55fanwen.com/")
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        updateService = retrofitUpdate.create(Api.class);

        // 下载进度

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ProgressInterceptor())
                .build();
        Retrofit retrofitDownProgress = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://download.appmod.net/")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        downProgressService = retrofitDownProgress.create(Api.class);


        Retrofit videoRetrofit = new Retrofit.Builder()
                .baseUrl("http://c.3g.163.com/")
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        vedioListService = videoRetrofit.create(Api.class);

    }

    /************************************************** API **************************************************************/

    // 登录
    public static Observable<UserData> getUserdata(String email, String password) {

        Call<LoginModel> call = sUserService.getUserdata(email, password);

        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, retrofit2.Response<LoginModel> response) {
                String nickname = response.body().getData().getNickname();
                int error = response.body().getError();
                Log.e("nickname", "nickname="+ nickname);
                ToastUtils.showToast("登陆了");
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

            }
        });

        return null;
    }

    // find首页
    public static ArrayList<FindModel.DataBeanX> getFindData(String page) {

        final ArrayList<FindModel.DataBeanX> list = new ArrayList<>();

        Call<FindModel> call = sUserService.getFindData(page);
        call.enqueue(new Callback<FindModel>() {
            @Override
            public void onResponse(Call<FindModel> call, retrofit2.Response<FindModel> response) {
                int error = response.body().getError();
                List<FindModel.DataBeanX> data = response.body().getData();
                Log.e("++++>", "error=" + error);
                Log.e("++++>data", "data=" + data.size());
                list.addAll(data);
            }

            @Override
            public void onFailure(Call<FindModel> call, Throwable t) {

            }
        });
        return list;
    }

    // 修改头像
    public static Observable<String> modifyInformation(String path) {

        Map<String, RequestBody> map = new HashMap<String, RequestBody>();

        File file = new File(path);
        map.put("uploadFace", RequestBody.create(MediaType.parse("image/png"),file));

        Call<String> call = sUserService.modifyInformation("1","guanwang","13","2","qq号测试","",map);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                String s = response.body().toString();
                Log.e("++++>modifyInformation", s);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        return null;
    }

    // 排行榜信息
    public static Observable<String> getRanking(String category, String type,
                                                final String page, final OnlineRankAdapter adapter,
                                                final List<OnlineInfo> mList, final CommonCallBack mCommonCallBack) {

        Call<String> ranking = xBService.getRanking(category + "", type + "", page+"");
        ranking.enqueue(new Callback<String>() {

            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                String s = response.body().toString();
                try {
                    JSONObject jsonObject = parseObject(s);
                    String error = jsonObject.getString("error");
                    String data = jsonObject.getString("data");
                    if ("0".equals(error)) {


                        if ("1".equals(page)) {
                            mList.clear();
                        }
                        List<OnlineInfo> list = new ArrayList<OnlineInfo>();
                        list = parseArray(data, OnlineInfo.class);
                        adapter.addData(list);
                        mCommonCallBack.onSuccess(error);
                    }else {
                        mCommonCallBack.onFailure(error);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    mCommonCallBack.onFailure("0");
                    Log.e("++++>", e.toString());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mCommonCallBack.onFailure("0");
            }
        });

        return null;
    }

    // 搜索结果
    public static Observable<String> searchList(String keyWord,final String page, final SearchListAdapter adapter,
                                                final List<OnlineInfo> mList, final CommonCallBack mCommonCallBack) {

        Call<String> stringCall = xBService.SearchList(keyWord, page + "");
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                String s = response.body().toString();
                if (page.equals("1")) {
                    mList.clear();
                }

                try {
                    org.json.JSONObject json = new org.json.JSONObject(s);
                    String error = json.getString("error");
                    String data = json.getString("data");
                    if (error.equals("0")) {
                        List<OnlineInfo> list = new ArrayList<OnlineInfo>();
                        list = parseArray(data, OnlineInfo.class);
                        adapter.addData(list);
                        mCommonCallBack.onSuccess(error);
                    } else {
                        mCommonCallBack.onFailure(error);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    mCommonCallBack.onFailure("0");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mCommonCallBack.onFailure("0");
            }
        });

        return null;
    }

    // 视屏列表
    public static Observable<String> videoList(final int startPage, final VideoListAdapter adapter,
                                               final List<VideoInfo> mList, final CommonCallBack mCommonCallBack) {

        Call<String> stringCall = vedioListService.videoList(startPage * 20 / 2);
        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, retrofit2.Response<String> response) {
                String body = response.body();

                if (OtherUtil.isNotEmpty(body)) {
                    try {
                        org.json.JSONObject jsonObject = new org.json.JSONObject(body);
                        String v9LG4B3A0 = jsonObject.getString("V9LG4B3A0");
                        List<VideoInfo> list = new ArrayList<VideoInfo>();
                        list = parseArray(v9LG4B3A0, VideoInfo.class);
                        adapter.addData(list);

                        Log.e("++++>list", list.size()+"");
                        mCommonCallBack.onSuccess("0");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        mCommonCallBack.onFailure("1");
                        Log.e("+++++>e.", e.toString());
                    }
                }else {
                    mCommonCallBack.onFailure("0");
                    Log.e("+++++>", "没有数据");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mCommonCallBack.onFailure("0");
                Log.e("+++++>t。", t.toString());
            }
        });

        return null;
    }

}
