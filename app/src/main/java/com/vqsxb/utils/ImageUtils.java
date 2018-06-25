package com.vqsxb.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ImageUtils {
    /**
     * 
     * 根据bitmap压缩图片质量
     * @param bitmap 未压缩的bitmap
     * @return 压缩后的bitmap
     */
    public static Bitmap cQuality(Bitmap bitmap){
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        int beginRate = 100;
        //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bOut);
        while(bOut.size()/1024/1024>100){  //如果压缩后大于100Kb，则提高压缩率，重新压缩
            beginRate -=10;
            bOut.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, beginRate, bOut);
        }
        ByteArrayInputStream bInt = new ByteArrayInputStream(bOut.toByteArray());
        Bitmap newBitmap = BitmapFactory.decodeStream(bInt);
        if(newBitmap!=null){
            return newBitmap;
        }else{
            return bitmap;
        }
    }


 /*   public static void imageBrowser(Context context, int position,
                                    List<String> list) {
        Intent intent = new Intent(context, ImgScannActivity.class);
        intent.putExtra("imageUrls", (Serializable) list);
        intent.putExtra("imageIndex", position);
        context.startActivity(intent);
    }*/

}
