package com.vqsxb.utils;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import com.vqsxb.R;

import java.util.Random;

/**
 * Created by Administrator on 2017/11/8.
 */

public class ColorUtil {
    private static int[] colors = {R.color.bg1,R.color.bg2,R.color.bg3,R.color.bg4,R.color.bg5,R.color.bg6,R.color.bg7,R.color.bg8,R.color.bg9,R.color.bg10,R.color.bg11,R.color.bg12,R.color.bg13,R.color.bg14,R.color.bg15,};
    public static int getRandomColor(){
        int i = new Random().nextInt(colors.length);
        return colors[i];
    }
    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }
}
