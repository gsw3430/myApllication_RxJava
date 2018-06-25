package com.vqsxb.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/30 0030.
 */

public class OnlineInfo {


    /**
     * appID : 496540
     * relation_type : 1
     * title : 荒野行动
     * icon : http://pic1.vqs.com/2017/1115/20171115112356686.png
     * rec_pic :
     * tag : [{"id":"1918","name":"网易"}]
     * downUrl_arr : ["https://g83.gdl.netease.com/g83_netease_netease.feifan_cpc2_dev_1.102.apk"]
     * packName : com.netease.hyxd
     * version : 1.102
     * md5 : c70d66461562be7d9a1c78b6c8a2449a
     * score : 10.0
     * commentTotal : 669
     * commentShow : 1
     * showFileSize : 385M
     * ruanjianzhuangtai : 2
     * ruanjianzhuangtai_name : 不删档测试
     * ioszhuangtai : 4
     * ioszhuangtai_name :
     */

    private String appID;
    private String relation_type;
    private String title;
    private String icon;
    private String rec_pic;
    private String packName;
    private String version;
    private String md5;
    private String score;
    private String commentTotal;
    private String commentShow;
    private String showFileSize;
    private String ruanjianzhuangtai;
    private String ruanjianzhuangtai_name;
    private String ioszhuangtai;
    private String ioszhuangtai_name;
    private List<TagBean> tag;
    private List<String> downUrl_arr;
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getRelation_type() {
        return relation_type;
    }

    public void setRelation_type(String relation_type) {
        this.relation_type = relation_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRec_pic() {
        return rec_pic;
    }

    public void setRec_pic(String rec_pic) {
        this.rec_pic = rec_pic;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCommentTotal() {
        return commentTotal;
    }

    public void setCommentTotal(String commentTotal) {
        this.commentTotal = commentTotal;
    }

    public String getCommentShow() {
        return commentShow;
    }

    public void setCommentShow(String commentShow) {
        this.commentShow = commentShow;
    }

    public String getShowFileSize() {
        return showFileSize;
    }

    public void setShowFileSize(String showFileSize) {
        this.showFileSize = showFileSize;
    }

    public String getRuanjianzhuangtai() {
        return ruanjianzhuangtai;
    }

    public void setRuanjianzhuangtai(String ruanjianzhuangtai) {
        this.ruanjianzhuangtai = ruanjianzhuangtai;
    }

    public String getRuanjianzhuangtai_name() {
        return ruanjianzhuangtai_name;
    }

    public void setRuanjianzhuangtai_name(String ruanjianzhuangtai_name) {
        this.ruanjianzhuangtai_name = ruanjianzhuangtai_name;
    }

    public String getIoszhuangtai() {
        return ioszhuangtai;
    }

    public void setIoszhuangtai(String ioszhuangtai) {
        this.ioszhuangtai = ioszhuangtai;
    }

    public String getIoszhuangtai_name() {
        return ioszhuangtai_name;
    }

    public void setIoszhuangtai_name(String ioszhuangtai_name) {
        this.ioszhuangtai_name = ioszhuangtai_name;
    }

    public List<TagBean> getTag() {
        return tag;
    }

    public void setTag(List<TagBean> tag) {
        this.tag = tag;
    }

    public List<String> getDownUrl_arr() {
        return downUrl_arr;
    }

    public void setDownUrl_arr(List<String> downUrl_arr) {
        this.downUrl_arr = downUrl_arr;
    }

    public static class TagBean {
        /**
         * id : 1918
         * name : 网易
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
