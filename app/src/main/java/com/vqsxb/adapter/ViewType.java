package com.vqsxb.adapter;

/**
 * Created by lx on 2017/9/20.
 * 1=随机推荐一个游戏
 * 2=随机推荐一个广告
 * 3=评论上墙
 * 4=新游预约
 * 5=玩家推荐游戏
 * 6=游戏横排
 * 7=推荐玩家
 * 8=自动轮换幻灯片
 * 9=来自编辑推荐卡片，和第一个相同布局
 * 10=热门推荐标签
 * 11=模块标签 
 */
public enum ViewType {
    NULL(0), MODULE1(1), MODULE2(2), MODULE3(3), MODULE4(4), MODULE5(5), MODULE6(6), MODULE7(7), MODULE8(8), MODULE9(9), MODULE10(10),MODULE11(11);

    private final int value;

    ViewType(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static ViewType valueOf(int value) {
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
            default:
                return NULL;
        }
    }
}
