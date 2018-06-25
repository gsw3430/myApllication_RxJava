package com.vqsxb.common;

import com.vqsxb.utils.Statistic;

/**
 * Created by Administrator on 2017/11/13 0013.
 */

public class Constant {
    /** 主域名 **/
    public final static String BASE_URL = "http://120.24.254.190:83/";
    public static String VERSION_CODE = Statistic.getAppVersionName();
    public static String VQS_QUDAO = Statistic.getChannel();

    public static String VERSION_QUDAO = "&version=" + Constant.VERSION_CODE + "&qudao=" + Constant.VQS_QUDAO;

    public static String CODE = "-----BEGIN CERTIFICATE-----\n" +
            "MIIFrjCCBJagAwIBAgIQbk68UeMr05Nr0XdMfsma/jANBgkqhkiG9w0BAQsFADBP\n" +
            "MQswCQYDVQQGEwJDTjEaMBgGA1UEChMRV29TaWduIENBIExpbWl0ZWQxJDAiBgNV\n" +
            "BAMMG0NBIOayg+mAmuWFjei0uVNTTOivgeS5piBHMjAeFw0xNjA4MzEwNjI5MDla\n" +
            "Fw0xOTA4MzEwNjI5MDlaMCMxCzAJBgNVBAYTAkNOMRQwEgYDVQQDDAthcGkudnFz\n" +
            "LmNvbTCCASIwDQYJKoZIhvcNAQEBBQADggEPADCCAQoCggEBALMsYdxFLJwgr+oU\n" +
            "uTNlKrDInTxgd9Rzrm+4tq9URl2wr07gsonqxaFIpl5h+JaiMu/oLxQkg7ocUzk2\n" +
            "cfsHobGZC2ErePvGiHsDibnZt/ckKpLrQdRS5X4AUm0FvT3gXg78tB4FvUKQiQYP\n" +
            "9kZ4b69+w1JCKuoRlY48D2f+MjS+uimf5hL+ATr8p6uPtWXJKEG++BSmkzg/DhQe\n" +
            "LahCtJ0luGdLj3gK/oyF5ipK7Okte3gWc1SGlkckcynQFmYxEFNupEIGqrkF8XAh\n" +
            "EIuITaHOD/1ftBIP7Ed5slGG0yOf9KbBQolIjK2JKS7ZqTKKDGLOKQfuqlJpTqh3\n" +
            "+ZH4Ba0CAwEAAaOCArAwggKsMA4GA1UdDwEB/wQEAwIFoDAdBgNVHSUEFjAUBggr\n" +
            "BgEFBQcDAgYIKwYBBQUHAwEwCQYDVR0TBAIwADAdBgNVHQ4EFgQUlvSwYjG0VrJX\n" +
            "F9rxv3Jy/qqcTecwHwYDVR0jBBgwFoAUMNp0hvMokFae1zExwr1ZzZMSOR0wfwYI\n" +
            "KwYBBQUHAQEEczBxMDUGCCsGAQUFBzABhilodHRwOi8vb2NzcDIud29zaWduLmNu\n" +
            "L2NhMmcyL3NlcnZlcjEvZnJlZTA4BggrBgEFBQcwAoYsaHR0cDovL2FpYTIud29z\n" +
            "aWduLmNuL2NhMmcyLnNlcnZlcjEuZnJlZS5jZXIwPgYDVR0fBDcwNTAzoDGgL4Yt\n" +
            "aHR0cDovL2NybHMyLndvc2lnbi5jbi9jYTJnMi1zZXJ2ZXIxLWZyZWUuY3JsMBYG\n" +
            "A1UdEQQPMA2CC2FwaS52cXMuY29tME8GA1UdIARIMEYwCAYGZ4EMAQIBMDoGCysG\n" +
            "AQQBgptRAQECMCswKQYIKwYBBQUHAgEWHWh0dHA6Ly93d3cud29zaWduLmNvbS9w\n" +
            "b2xpY3kvMIIBBAYKKwYBBAHWeQIEAgSB9QSB8gDwAHYAaPaY+B9kgr46jO65KB1M\n" +
            "/HFRXWeT1ETRCmesu09P+8QAAAFW32iZKgAABAMARzBFAiBIz2zWIP5IvAumbfC8\n" +
            "fP1s3HgGP10wEleu0WLl8nXtqgIhAKNRgI+lmDL2CyOomfVKZmcr7jbtmGzff0M/\n" +
            "lvS0sGVQAHYApLkJkLQYWBSHuxOizGdwCjw1mAT5G9+443fNDsgN3BAAAAFW32iZ\n" +
            "0gAABAMARzBFAiAGxuCsNykkl4yyrThobRBWLy8HnW+wyfeSY20bd1KbcgIhAI/+\n" +
            "BMdwZ7+1sIh1Utfajf6HRxW0qledCsVVilXzXLjOMA0GCSqGSIb3DQEBCwUAA4IB\n" +
            "AQAWFmi2yErFhtw+nfZMI9Y3QdDkB7Kyywblsh0jHXyduc55vvXSMwcmRKt/zlB5\n" +
            "wnD1wB+mkcsntHBopsh1OuDlYAUoV8WhARQRh/xCwNIqGSgoAsLQtgG0nF7diJnW\n" +
            "EjTWOaAazaN8qEejOi/bWc68/p11HEgGqXtaXoONKKnX2ieqYTZ0HmeTIaydxYLy\n" +
            "07vaofn80isk7/HFRAY/fTQI5Sj+Ce7wOH89dC+VCQeW8HIU2ZsrgRgj5vP+qJih\n" +
            "4W3c/YM1cdafb+bw7cgTRfdd6Bscs5c4VnLLAwpyVU2/42wJmq3l6WUNPUlBROlV\n" +
            "JEa7KJdsa7bXe8uo18uNaQB1\n" +
            "-----END CERTIFICATE-----\n";


    // 首页接口
    public static String URL_HOME = "/index.php?m=app&c=index&a=index" + VERSION_QUDAO;


    /**推荐页**/
    public final static String INTERFACE_RECOMMEND = "/index.php?m=app&c=index&a=index" + VERSION_QUDAO;


    // video传递数据
    public static String VIDEOINFO = "video info";
    // 下载中心
    public static final String INDEX_KEY = "IndexKey";

}
