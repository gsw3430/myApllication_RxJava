package com.vqsxb.utils;

import android.content.Context;

import java.util.List;

/**
 * 通用工具类
 *
 * @author lx
 */
public class OtherUtil {

    public static boolean isEmpty(Object obj) {
        try {
            if (obj == null) {
                return true;
            }
            String str = String.valueOf(obj);
            if (str.equals("null")) {
                return true;
            }
            if (str.equals("")) {
                return true;
            }
            str = null;
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean isNotEmpty(Object obj) {
        try {
            if (obj == null) {
                return false;
            }
            String str = String.valueOf(obj);
            if (str.equals("null")) {
                return false;
            }
            if (str.equals("")) {
                return false;
            }
            str = null;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static int dip2px(Context context, float dpValue) {
        return ((int) (dpValue
                * context.getResources().getDisplayMetrics().density + 0.5f));
    }

    /**
     * list的非空判断
     * @param list
     * @return
     */
    public static <T> boolean isListNotEmpty(List<T> list) {
        try {
            if (list == null) {
                return false;
            }
            if (list.size() <= 0) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
