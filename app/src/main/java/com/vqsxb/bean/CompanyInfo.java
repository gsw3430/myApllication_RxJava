package com.vqsxb.bean;

/**
 * Created by Administrator on 2017/11/30 0030.
 */

public class CompanyInfo {


    /**
     * id : 2300
     * title : Gameloft
     * thumb : http://pic1.vqs.com/2017/0213/20170213092052473.png
     * commentScore : 4
     * is_attention : 0
     */

    private String id;
    private String title;
    private String thumb;
    private String commentScore;
    private String is_attention;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getCommentScore() {
        return commentScore;
    }

    public void setCommentScore(String commentScore) {
        this.commentScore = commentScore;
    }

    public String getIs_attention() {
        return is_attention;
    }

    public void setIs_attention(String is_attention) {
        this.is_attention = is_attention;
    }
}
