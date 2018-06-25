package com.vqsxb.viewholder.gamedetails;

/**
 * Created by gb on 2017/9/23.
 * 1=标签
 * 2=修改了什么
 * 3=详情图片
 * 4=详情介绍
 * 5= 游戏(破解 汉化...)
 * 6=礼包
 * 7=返利
 * 8=详细信息
 * 9=推荐游戏
 * 10=每日发现
 */
public enum ViewTypeAPPDetail {
  NULL(0),   MODULE1(1), MODULE2(2), MODULE3(3), MODULE4(4), MODULE5(5), MODULE6(6), MODULE7(7), MODULE8(8), MODULE9(9), MODULE10(10), MODULE11(11)
    , MODULE12(12), MODULE13(13);

    private final int value;

    ViewTypeAPPDetail(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static ViewTypeAPPDetail valueOf(int value) {
        switch (value) {
            case 1:
                return MODULE1;
            case 2:
                return MODULE2;
            case 3:
                return MODULE3;
            case 4:
                return MODULE4;
            case 5:
                return MODULE5;
            case 6:
                return MODULE6;
            case 7:
                return MODULE7;
            case 8:
                return MODULE8;
            case 9:
                return MODULE9;
            case 10:
                return MODULE10;
            case 11:
                return MODULE11;
            case 12:
                return MODULE12;
            case 13:
                return MODULE13;
            default:
                return NULL;
        }
    }
}
