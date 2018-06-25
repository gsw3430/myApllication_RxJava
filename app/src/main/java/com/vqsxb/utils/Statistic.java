package com.vqsxb.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import com.vqsxb.common.Constant;

import org.xutils.common.Callback.CommonCallback;
import org.xutils.x;

import java.util.HashMap;
import java.util.Map;

/**
 * 下载统计
 * 
 * @author lx 20160706
 */
public class Statistic {
	/**
	 * 统计方法： 1.下载统计：点击下载按钮执行下载方法时进行统计（appid, pid , posid , userid, version）
	 * 2.下载失败统计：下载失败时进行统计（gameid ,downUrl , version , error）
	 * 3.取消下载统计：取消下载时进行统计（gameid , downUrl ,version , pro）
	 */
	public static String STATISTIC_DOWN = Constant.BASE_URL + "/index.php?m=vqsNew&c=discover&a=hitslog&"; // 点击下载统计
	public static String STATISTIC_ERROR_DEL = Constant.BASE_URL + "/index.php?m=vqsNew&c=discover&a=downErrorLog";//下载错误和删除下载统计

	/**
	 * 下载统计
	 * 
	 * @param appid
	 * @param pid
	 *            位置信息
	 * @param posid
	 *            位置信息
	 * @param userid
	 * type 豌豆荚 1 ，0是助手
	 * 
	 */
	public static void StatisticDown(String appid, String pid, String posid, String userid, String type, String pkg) {
		Map<String, Object> map = new HashMap<>();
		map.put("gameid", appid);
		map.put("pid", pid);
		map.put("posid", posid);
		map.put("userid", userid);
		map.put("type", type);
		map.put("package", pkg);
		HttpUtil.Post(STATISTIC_DOWN, map,new CommonCallback<String>() {

			@Override
			public void onCancelled(CancelledException arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onError(Throwable arg0, boolean arg1) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFinished() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(String arg0) {
			}
		});
	}

	/** 
	 * 返回当前程序版本名 
	 */  
	public static String getAppVersionName() {
	    String versionName = "";
	    try {  
	        PackageManager pm = x.app().getPackageManager();
	        PackageInfo pi = pm.getPackageInfo(x.app().getPackageName(), 0);
	        versionName = pi.versionName;  
	        if (versionName == null || versionName.length() <= 0) {  
	            return "";  
	        }  
	    } catch (Exception e) {
	       e.printStackTrace();
	    }  
	    return versionName;  
	}  
	
	/**
	 * 获取渠道的信息
	 * @return
	 */
	public static String getChannel(){
		String channel=null;
		try {
		 ApplicationInfo applicationInfo= x.app().getPackageManager().getApplicationInfo(x.app().getPackageName(), PackageManager.GET_META_DATA);
		

		 try{
			 channel = 	applicationInfo.metaData.getString("UMENG_CHANNEL");
		 }catch (Exception e){
		  /*int  code  =applicationInfo.metaData.getInt("UMENG_CHANNEL");
			channel = code +"";*/
		 }


		/* if(OtherUtils.isEmpty(channel)){
				return "";
			}*/
		 
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return channel;
	}
}