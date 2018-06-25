package com.vqsxb.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/1 0001.
 */

public class GameDetailsInfo {


    /**
     * info : {"name":"标签","type":"2","gameid":"497940","title":"光荣使命：使命行动"}
     * data : [{"id":"16","name":"动作"},{"id":"40","name":"射击"},{"id":"8881","name":"吃鸡"},{"id":"59","name":"3D"}]
     */

    private InfoBean info;
    private List<DataBean> data;

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class InfoBean {
        /**
         * name : 标签
         * type : 2
         * gameid : 497940
         * title : 光荣使命：使命行动
         * subname :
         * model_type : 6
         * icon_type :
         * load_type : position
         * load_value : 17
         * load_order_type :
         * load_more : 1
         * model_from : 1
         */



        private String name;
        private String type;
        private String gameid;
        private String title;
        private String subname;
        private String model_type;
        private String icon_type;
        private String load_type;
        private String load_value;
        private String load_order_type;
        private String load_more;
        private String model_from;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getGameid() {
            return gameid;
        }

        public void setGameid(String gameid) {
            this.gameid = gameid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubname() {
            return subname;
        }

        public void setSubname(String subname) {
            this.subname = subname;
        }

        public String getModel_type() {
            return model_type;
        }

        public void setModel_type(String model_type) {
            this.model_type = model_type;
        }

        public String getIcon_type() {
            return icon_type;
        }

        public void setIcon_type(String icon_type) {
            this.icon_type = icon_type;
        }

        public String getLoad_type() {
            return load_type;
        }

        public void setLoad_type(String load_type) {
            this.load_type = load_type;
        }

        public String getLoad_value() {
            return load_value;
        }

        public void setLoad_value(String load_value) {
            this.load_value = load_value;
        }

        public String getLoad_order_type() {
            return load_order_type;
        }

        public void setLoad_order_type(String load_order_type) {
            this.load_order_type = load_order_type;
        }

        public String getLoad_more() {
            return load_more;
        }

        public void setLoad_more(String load_more) {
            this.load_more = load_more;
        }

        public String getModel_from() {
            return model_from;
        }

        public void setModel_from(String model_from) {
            this.model_from = model_from;
        }
    }

    public static class DataBean {
        /**
         * id : 16
         * name : 动作
         * content : http://pic1.vqs.com/2017/1108/20171108104401284.png?x-oss-process=image/resize,h_360|http://pic1.vqs.com/2017/1108/20171108104401187.png?x-oss-process=image/resize,h_360|http://pic1.vqs.com/2017/1108/20171108104401366.png?x-oss-process=image/resize,h_360|http://pic1.vqs.com/2017/1108/20171108104402824.png?x-oss-process=image/resize,h_360|http://pic1.vqs.com/2017/1108/20171108104402356.png?x-oss-process=image/resize,h_360
         * downcount : 2699
         * showFileSize : 298M
         * version : 1.0.0
         * updatetime : 2017-11-25
         * developer : 腾讯
         * developer_id :
         */



        private String id;
        private String name;
        private String content;
        private String downcount;
        private String showFileSize;
        private String version;
        private String updatetime;
        private String developer;
        private String developer_id;
        /**
         * appID : 496540
         * relation_type : 1
         * title : 荒野行动
         * icon : http://pic1.vqs.com/2017/1115/20171115112356686.png
         * rec_pic :
         * tag : [{"id":"1918","name":"网易"}]
         * downUrl_arr : ["https://g83.gdl.netease.com/g83_netease_netease.feifan_cpc2_dev_1.102.apk"]
         * packName : com.netease.hyxd
         * md5 : c70d66461562be7d9a1c78b6c8a2449a
         * score : 9.4
         * commentTotal : 564
         * commentShow : 1
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
        private String md5;
        private String score;
        private String commentTotal;
        private String commentShow;
        private String ruanjianzhuangtai;
        private String ruanjianzhuangtai_name;
        private String ioszhuangtai;
        private String ioszhuangtai_name;
        private List<DataBean> tag;
        private List<String> downUrl_arr;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDowncount() {
            return downcount;
        }

        public void setDowncount(String downcount) {
            this.downcount = downcount;
        }

        public String getShowFileSize() {
            return showFileSize;
        }

        public void setShowFileSize(String showFileSize) {
            this.showFileSize = showFileSize;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getDeveloper() {
            return developer;
        }

        public void setDeveloper(String developer) {
            this.developer = developer;
        }

        public String getDeveloper_id() {
            return developer_id;
        }

        public void setDeveloper_id(String developer_id) {
            this.developer_id = developer_id;
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

        public List<DataBean> getTag() {
            return tag;
        }

        public void setTag(List<DataBean> tag) {
            this.tag = tag;
        }

        public List<String> getDownUrl_arr() {
            return downUrl_arr;
        }

        public void setDownUrl_arr(List<String> downUrl_arr) {
            this.downUrl_arr = downUrl_arr;
        }
    }
}
