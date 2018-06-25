package com.vqsxb.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/16 0016.
 */

public class RecommendInfo {


    /**
     * info : {"name":"","subname":"","model_from":"1","model_type":"9","icon_type":"","load_type":"","load_value":"","load_order_type":"","load_more":"0"}
     * data : [{"appID":"498193","icon":"http://pic1.vqs.com/2017/1109/20171109120125295.png","title":"太阳：起源","briefContent":"太阳起源之地，你想探索吗？","rec_pic":"http://pic1.vqs.com/Uploads/2017-11-27/1511762804330.png","views":"8937","score":"8.0","commentTotal":"79","commentShow":"1","showFileSize":"367.71M","downCount":"8937","relation_type":"1","time":"来自编辑11月27日推荐"}]
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
         * name :
         * subname :
         * model_from : 1
         * model_type : 9
         * icon_type :
         * load_type :
         * load_value :
         * load_order_type :
         * load_more : 0
         */

        private String name;
        private String subname;
        private String model_from;
        private String model_type;
        private String icon_type;
        private String load_type;
        private String load_value;
        private String load_order_type;
        private String load_more;



        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSubname() {
            return subname;
        }

        public void setSubname(String subname) {
            this.subname = subname;
        }

        public String getModel_from() {
            return model_from;
        }

        public void setModel_from(String model_from) {
            this.model_from = model_from;
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
    }

    public static class DataBean {
        /**
         * appID : 498193
         * icon : http://pic1.vqs.com/2017/1109/20171109120125295.png
         * title : 太阳：起源
         * briefContent : 太阳起源之地，你想探索吗？
         * rec_pic : http://pic1.vqs.com/Uploads/2017-11-27/1511762804330.png
         * views : 8937
         * score : 8.0
         * commentTotal : 79
         * commentShow : 1
         * showFileSize : 367.71M
         * downCount : 8937
         * relation_type : 1
         * time : 来自编辑11月27日推荐
         */

        private String appID;
        private String icon;
        private String title;
        private String briefContent;
        private String rec_pic;
        private String views;
        private String score;
        private String commentTotal;
        private String commentShow;
        private String showFileSize;
        private String downCount;
        private String relation_type;
        private String time;
        /**
         * userid : 34955227
         * nickname : 可乐味樱桃
         * chenhao_pic :
         * avatar : https://img3.tapimg.com/avatars/57718ab2e5f03058ed62e8b344cea118.jpg
         * chenhao_title :
         * card_id : 1380006
         * commentid : 483524
         * content : 希望大家一起交流，游戏很有可玩性，好友邀请码<br />COSE DUGU ZAVO 2279，输入邀请码，领50钻
         * support : 0
         * reply_count : 0
         * userscore : 5
         * is_support : 0
         * creat_at : 3小时前
         */

        private String userid;
        private String nickname;
        private String chenhao_pic;
        private String avatar;
        private String chenhao_title;
        private String card_id;
        private String commentid;
        private String content;
        private String support;
        private String reply_count;
        private String userscore;
        private String is_support;
        private String creat_at;


        public String getAppID() {
            return appID;
        }

        public void setAppID(String appID) {
            this.appID = appID;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBriefContent() {
            return briefContent;
        }

        public void setBriefContent(String briefContent) {
            this.briefContent = briefContent;
        }

        public String getRec_pic() {
            return rec_pic;
        }

        public void setRec_pic(String rec_pic) {
            this.rec_pic = rec_pic;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
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

        public String getDownCount() {
            return downCount;
        }

        public void setDownCount(String downCount) {
            this.downCount = downCount;
        }

        public String getRelation_type() {
            return relation_type;
        }

        public void setRelation_type(String relation_type) {
            this.relation_type = relation_type;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getChenhao_pic() {
            return chenhao_pic;
        }

        public void setChenhao_pic(String chenhao_pic) {
            this.chenhao_pic = chenhao_pic;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getChenhao_title() {
            return chenhao_title;
        }

        public void setChenhao_title(String chenhao_title) {
            this.chenhao_title = chenhao_title;
        }

        public String getCard_id() {
            return card_id;
        }

        public void setCard_id(String card_id) {
            this.card_id = card_id;
        }

        public String getCommentid() {
            return commentid;
        }

        public void setCommentid(String commentid) {
            this.commentid = commentid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getSupport() {
            return support;
        }

        public void setSupport(String support) {
            this.support = support;
        }

        public String getReply_count() {
            return reply_count;
        }

        public void setReply_count(String reply_count) {
            this.reply_count = reply_count;
        }

        public String getUserscore() {
            return userscore;
        }

        public void setUserscore(String userscore) {
            this.userscore = userscore;
        }

        public String getIs_support() {
            return is_support;
        }

        public void setIs_support(String is_support) {
            this.is_support = is_support;
        }

        public String getCreat_at() {
            return creat_at;
        }

        public void setCreat_at(String creat_at) {
            this.creat_at = creat_at;
        }
    }
}
