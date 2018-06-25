package com.vqsxb.utils;

import com.vqsxb.common.Constant;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback.Cancelable;
import org.xutils.common.Callback.CommonCallback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class HttpUtil {

    static int i = 0;

    public static void Get(final String url, final Map<String, Object> map, final CommonCallback<String> callback) {
        if (i < 3) {
            String finalurl = Constant.BASE_URL + url;
            Get1(finalurl, map, new CommonCallback<String>() {

                @Override
                public void onSuccess(String arg0) {
                    callback.onSuccess(arg0);
                }

                @Override
                public void onError(Throwable arg0, boolean arg1) {
                    i++;
                    Get(url, map, callback);


                }

                @Override
                public void onFinished() {


                }

                @Override
                public void onCancelled(CancelledException arg0) {

                }


            });

        } else {
            String finalurl = Constant.BASE_URL + url;
            Get1(finalurl, map, callback);
        }


    }


    public static void Post(final String url, final Map<String, Object> map, final CommonCallback<String> callback) {
        if (i < 3) {
            String finalurl = Constant.BASE_URL + url;
            Post1(finalurl, map, new CommonCallback<String>() {

                @Override
                public void onSuccess(String arg0) {
                    callback.onSuccess(arg0);
                }

                @Override
                public void onError(Throwable arg0, boolean arg1) {
                    i++;
                    Post(url, map, callback);

                }

                @Override
                public void onFinished() {


                }

                @Override
                public void onCancelled(CancelledException arg0) {

                }


            });
        } else {
            String finalurl = Constant.BASE_URL + url;
            Post1(finalurl, map, callback);


        }

    }


    /**
     * http get
     *
     * @param url
     * @param map
     * @param callback
     * @return
     */
    public static <T> Cancelable Get1(String url, Map<String, Object> map, CommonCallback<T> callback) {

        RequestParams params = new RequestParams(url);

        try {
            if (null != map) {
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    params.addQueryStringParameter(entry.getKey(), entry.getValue().toString());
                }
                params.setSslSocketFactory(setSocketFactory());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Cancelable cancelable = x.http().get(params, callback);


        return cancelable;

    }

    /**
     * i
     * http post
     *
     * @param url
     * @param map
     * @param callback
     * @return
     */
    public static <T> Cancelable Post1(String url, Map<String, Object> map, CommonCallback<T> callback) {

        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
            params.addBodyParameter("version", Constant.VERSION_CODE);
            params.addBodyParameter("qudao", Constant.VQS_QUDAO);
            //params.setSslSocketFactory(setSocketFactory());

        }
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }

    public static <T> Cancelable Post_w(String url, Map<String, Object> map, CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
             params.addBodyParameter("version", Constant.VERSION_CODE);
            params.addBodyParameter("qudao", Constant.VQS_QUDAO);
        }
        Cancelable cancelable = x.http().post(params, callback);
        return cancelable;
    }


    /**
     * 文件上传
     *
     * @param url
     * @param map
     * @param callback
     * @return
     */
    public static <T> Cancelable UploadFile1(String url, Map<String, Object> map, CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }
        }
        params.setMultipart(true);

        Cancelable cancelable = x.http().post(params, callback); // get

        return cancelable;

    }


    public static void UploadFile(final String url, final Map<String, Object> map, final CommonCallback<String> callback) {
        if (i < 3) {
            String finalurl = Constant.BASE_URL + url;
            UploadFile1(finalurl, map, new CommonCallback<String>() {
                @Override
                public void onError(Throwable arg0, boolean arg1) {
                    i++;
                    UploadFile(url, map, callback);

                }

                @Override
                public void onSuccess(String arg0) {
                    callback.onSuccess(arg0);

                }

                @Override
                public void onCancelled(CancelledException arg0) {
                    // TODO Auto-generated method stub

                }


                @Override
                public void onFinished() {
                    // TODO Auto-generated method stub

                }


            });

        } else {
            String finalurl = Constant.BASE_URL + url;
            UploadFile1(finalurl, map, callback);
        }


    }


    /**
     * 文件下载
     *
     * @param url
     * @param path
     * @param callback
     * @return
     */
    public static <T> Cancelable DownLoadFile(String url, String path, CommonCallback<T> callback) {

        RequestParams params = new RequestParams(url);
        params.setAutoResume(true);//设置断点续传
        params.setSaveFilePath(path); //设置文件保存的路径
        Cancelable cancelable = x.http().get(params, callback);
        return cancelable;

    }

    public static <T> Cancelable UploadData(String url, Map<String, Object> map, Map<String, File> map2, CommonCallback<T> callback) {
        RequestParams params = new RequestParams(url);
        if (null != map) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                params.addParameter(entry.getKey(), entry.getValue());
            }

            if (map2 != null) {
                for (Entry<String, File> entry : map2.entrySet()) {
                    params.addBodyParameter("filename[]", entry.getValue());
                }
            }
        }
        params.setMultipart(true);

        Cancelable cancelable = x.http().post(params, callback); // get

        return cancelable;

    }

    public static SSLSocketFactory setSocketFactory() {
        InputStream in = null;
        SSLContext context = null;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
//		    in =  new BufferedInputStream( getAssets().open("vqs.cer"));
            in = new ByteArrayInputStream(Constant.CODE.getBytes());
            Certificate ca = cf.generateCertificate(in);

            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);
            TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            factory.init(keyStore);
            context = SSLContext.getInstance("TLS");
            context.init(null, factory.getTrustManagers(), null);
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return context.getSocketFactory();


    }


    /**
     * 获取视频的地址
     *
     * @param ppid
     * @param t
     * @param k
     * @return
     */
    public static String GetVideoUrl(String ppid, String t, String k) {
        String result = "";

        try {

            URL postUrl = new URL("http://server.m.pp.cn/api/proxyNew");

            HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();


            connection.setDoOutput(true);

            connection.setDoInput(true);

            connection.setRequestMethod("POST");


            connection.setUseCaches(false);

            connection.setInstanceFollowRedirects(true);


            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setRequestProperty("Host", "server.m.pp.cn");

            connection.setRequestProperty("Referer", "");

            connection.connect();
            DataOutputStream out = new DataOutputStream(connection
                    .getOutputStream());

            String content = "urlKey=" + URLEncoder.encode("resource.subscription.getWebDetail", "UTF-8") + "&uc_param_str=" + URLEncoder.encode("frvecpmintnidnut", "UTF-8")
                    + "&id=" + URLEncoder.encode(ppid, "UTF-8") + "&t=" + URLEncoder.encode(t, "UTF-8") + "&k=" + URLEncoder.encode(k, "UTF-8");


            out.writeBytes(content);
            out.flush();
            out.close();

            if (connection.getResponseCode() == 200) {
                String data = getJsonByInputstream(connection.getInputStream(), "UTF-8");
                result = parseData(data);
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }


    public static String getJsonByInputstream(InputStream is, String charset) {
        String info = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is, charset));
            String temp = null;
            StringBuilder sb = new StringBuilder();
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            info = sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert br != null;
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return info;
    }


    private static String parseData(String result) {
        String url = "";
        try {
            JSONObject jo = new JSONObject(result);
            String data = jo.getString("data");
            JSONObject joo = new JSONObject(data);
            String content = joo.getString("content");
            JSONObject jsonObject = new JSONObject(content);
            String tempvideo = jsonObject.getString("videoEx");
            JSONObject finaljo = new JSONObject(tempvideo);
            url = finaljo.getString("url");


        } catch (JSONException e) {

            e.printStackTrace();
        }
        return url;
    }
}
