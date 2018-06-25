package com.vqsxb.httputils;

import com.vqsxb.bean.find.FindModel;
import com.vqsxb.bean.user.LoginModel;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

import static com.vqsxb.httputils.ApiService.CACHE_CONTROL_NETWORK;

/**
 * Created by Administrator on 2017/11/15 0015.
 */

public interface Api {

    // 登录
    // https://api.appmod.net/index.php?m=app&c=member&a=login&email=2606502052@qq.com&password=123456789
    @Headers(CACHE_CONTROL_NETWORK)
    @POST("index.php?m=app&c=member&a=login")  ///@Query("ip")String ip
    Call<LoginModel> getUserdata(@Query("email") String email, @Query("password") String password);

    // find首页
    // https://api.appmod.net/index.php?m=app&c=discover&a=index
    @Headers(CACHE_CONTROL_NETWORK)
    @POST("index.php?m=app&c=discover&a=index")  ///@Query("ip")String ip
    Call<FindModel> getFindData(@Query("page") String page);

    // 首页推荐数据
    // http://120.24.254.190:83/index.php?m=app&c=index&a=index&page=1&drop=1
    @POST("index.php?m=app&c=index&a=index")  ///@Query("ip")String ip
    Call<String> getRecommentData(@Query("page") String page, @Query("drop") String drop);
    // 发现的数据
    @POST("index.php?m=app&c=discover&a=index")
    Call<String> getDiscoveryData(@Query("page") String page, @Query("time") String time);
    // 排行榜
    @POST("index.php?m=app&c=rank&a=items")
    Call<String> getRanking(@Query("category") String category, @Query("type") String type, @Query("page") String page);
    // 厂商排行
    @POST("index.php?m=app&c=rank&a=changshang_items")
    Call<String> getCompanyRank(@Query("page") String page);
    // 游戏详情
    @POST("index.php?m=app&c=content&a=content")
    Call<String> APPDetails(@Query("appid") String appid);
    // 热搜
    @POST("index.php?m=app&c=search&a=hot")
    Call<String> SearchHot();
    // 搜索结果
    @POST("index.php?m=app&c=search&a=item")
    Call<String> SearchList(@Query("keyword") String keyword, @Query("page") String page);
    // 搜索提示
    @POST("index.php?m=app&c=search&a=tip")
    Call<String> SearchHint(@Query("keyword") String keyword);

    // 修改资料
    // https://api.appmod.net/index.php?m=app&c=member&a=userinfo &version=1&qudao=guanwang&userid=13&sex=1&nickname=qq&sign=null&uploadFace=null
    @Multipart
    @POST("index.php?m=app&c=member&a=userinfo")  ///@Query("ip")String ip
    Call<String> modifyInformation( @Query("version") String version,
                                    @Query("qudao") String qudao,
                                    @Query("userid") String userid,
                                    @Query("sex") String sex,
                                    @Query("nickname") String nickname,
                                    @Query("sign") String sign,
                                    @PartMap Map<String, RequestBody> params);

    // 更新
    @POST("index.php/index/upversion")
    Call<String> updateIfo(@Query("version") String version, @Query("package") String packages);

    // 下载更新进度
    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);

    // 视屏列表 必须要加headers
    @Headers("User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11")
    @GET("nc/video/list/V9LG4B3A0/n/{startPage}-10.html")
    Call<String> videoList(@Path("startPage") int startPage);


}
