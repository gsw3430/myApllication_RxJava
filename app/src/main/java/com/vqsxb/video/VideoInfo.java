package com.vqsxb.video;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/5 0005.
 */
@Entity
public class VideoInfo implements Parcelable {


    /**
     * sizeHD : 0
     * videoTopic : {"ename":"T1498037795416","tname":"明星大剧抢先看","alias":"最新热剧、最火花絮、最多明星","topic_icons":"http://dingyue.nosdn.127.net/5scL8DIaNxlw335R3hNvnCN306qL3mhJdUW9Y84XsGyUm1498037794827.jpg","tid":"T1498037795416"}
     * mp4Hd_url : null
     * description : 将军在上
     * title : 将军在上：男子爱上她已经好几年了，三个时辰都等不了了
     * mp4_url : http://flv3.bn.netease.com/videolib3/1712/05/nFiza2518/SD/nFiza2518-mobile.mp4
     * vid : VW3V496PE
     * cover : http://vimg3.ws.126.net/image/snapshot/2017/12/P/F/VW3V496PF.jpg
     * sizeSHD : 0
     * playersize : 1
     * ptime : 2017-12-05 10:55:18
     * m3u8_url : http://flv.bn.netease.com/videolib3/1712/05/nFiza2518/SD/movie_index.m3u8
     * topicImg : http://vimg3.ws.126.net/image/snapshot/2017/6/N/7/VCMI06DN7.jpg
     * votecount : 0
     * length : 100
     * videosource : 新媒体
     * m3u8Hd_url : null
     * sizeSD : 4350
     * topicSid : VCMI06DMH
     * playCount : 0
     * replyCount : 0
     * replyBoard : video_bbs
     * replyid : W3V496PE050835RB
     * topicName : 明星大剧抢先看
     * sectiontitle :
     * topicDesc : 每日专注分享精彩影视片段、热播大剧预告、明星综艺花絮以及各类搞笑剧情。
     */
    @Id
    private String vid;
    private int sizeHD;
  //  private VideoTopicBean videoTopic;
    private String mp4Hd_url;
    private String description;
    private String title;
    private String mp4_url;
    private String cover;
    private int sizeSHD;
    private int playersize;
    private String ptime;
    private String m3u8_url;
    private String topicImg;
    private int votecount;
    private int length;
    private String videosource;
    private String m3u8Hd_url;
    private int sizeSD;
    private String topicSid;
    private int playCount;
    private int replyCount;
    private String replyBoard;
    private String replyid;
    private String topicName;
    private String sectiontitle;
    private String topicDesc;

    /**
     * 下载地址，可能有多个视频源，统一用一个字段
     */
    private String videoUrl;

    /**
     * 文件大小，字节
     */
    private long totalSize;
    /**
     * 已加载大小
     */
    private long loadedSize;
    /**
     * 下载状态
     */
    private int downloadStatus = DownloadStatus.NORMAL;
    /**
     * 下载速度
     */
    private long downloadSpeed;

    @Generated(hash = 1047586363)
    public VideoInfo(String vid, int sizeHD, String mp4Hd_url, String description, String title, String mp4_url, String cover, int sizeSHD, int playersize, String ptime, String m3u8_url, String topicImg, int votecount,
            int length, String videosource, String m3u8Hd_url, int sizeSD, String topicSid, int playCount, int replyCount, String replyBoard, String replyid, String topicName, String sectiontitle, String topicDesc,
            String videoUrl, long totalSize, long loadedSize, int downloadStatus, long downloadSpeed) {
        this.vid = vid;
        this.sizeHD = sizeHD;
        this.mp4Hd_url = mp4Hd_url;
        this.description = description;
        this.title = title;
        this.mp4_url = mp4_url;
        this.cover = cover;
        this.sizeSHD = sizeSHD;
        this.playersize = playersize;
        this.ptime = ptime;
        this.m3u8_url = m3u8_url;
        this.topicImg = topicImg;
        this.votecount = votecount;
        this.length = length;
        this.videosource = videosource;
        this.m3u8Hd_url = m3u8Hd_url;
        this.sizeSD = sizeSD;
        this.topicSid = topicSid;
        this.playCount = playCount;
        this.replyCount = replyCount;
        this.replyBoard = replyBoard;
        this.replyid = replyid;
        this.topicName = topicName;
        this.sectiontitle = sectiontitle;
        this.topicDesc = topicDesc;
        this.videoUrl = videoUrl;
        this.totalSize = totalSize;
        this.loadedSize = loadedSize;
        this.downloadStatus = downloadStatus;
        this.downloadSpeed = downloadSpeed;
    }

    @Generated(hash = 296402066)
    public VideoInfo() {
    }


    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public long getLoadedSize() {
        return loadedSize;
    }

    public void setLoadedSize(long loadedSize) {
        this.loadedSize = loadedSize;
    }

    public int getDownloadStatus() {
        return downloadStatus;
    }

    public void setDownloadStatus(int downloadStatus) {
        this.downloadStatus = downloadStatus;
    }

    public long getDownloadSpeed() {
        return downloadSpeed;
    }

    public void setDownloadSpeed(long downloadSpeed) {
        this.downloadSpeed = downloadSpeed;
    }

    public int getSizeHD() {
        return sizeHD;
    }

    public void setSizeHD(int sizeHD) {
        this.sizeHD = sizeHD;
    }

   /* public VideoTopicBean getVideoTopic() {
        return videoTopic;
    }

    public void setVideoTopic(VideoTopicBean videoTopic) {
        this.videoTopic = videoTopic;
    }*/

    public String getMp4Hd_url() {
        return mp4Hd_url;
    }

    public void setMp4Hd_url(String mp4Hd_url) {
        this.mp4Hd_url = mp4Hd_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMp4_url() {
        return mp4_url;
    }

    public void setMp4_url(String mp4_url) {
        this.mp4_url = mp4_url;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getSizeSHD() {
        return sizeSHD;
    }

    public void setSizeSHD(int sizeSHD) {
        this.sizeSHD = sizeSHD;
    }

    public int getPlayersize() {
        return playersize;
    }

    public void setPlayersize(int playersize) {
        this.playersize = playersize;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getM3u8_url() {
        return m3u8_url;
    }

    public void setM3u8_url(String m3u8_url) {
        this.m3u8_url = m3u8_url;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public int getVotecount() {
        return votecount;
    }

    public void setVotecount(int votecount) {
        this.votecount = votecount;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getVideosource() {
        return videosource;
    }

    public void setVideosource(String videosource) {
        this.videosource = videosource;
    }

    public String getM3u8Hd_url() {
        return m3u8Hd_url;
    }

    public void setM3u8Hd_url(String m3u8Hd_url) {
        this.m3u8Hd_url = m3u8Hd_url;
    }


    public int getSizeSD() {
        return sizeSD;
    }

    public void setSizeSD(int sizeSD) {
        this.sizeSD = sizeSD;
    }

    public String getTopicSid() {
        return topicSid;
    }

    public void setTopicSid(String topicSid) {
        this.topicSid = topicSid;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getReplyBoard() {
        return replyBoard;
    }

    public void setReplyBoard(String replyBoard) {
        this.replyBoard = replyBoard;
    }

    public String getReplyid() {
        return replyid;
    }

    public void setReplyid(String replyid) {
        this.replyid = replyid;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getSectiontitle() {
        return sectiontitle;
    }

    public void setSectiontitle(String sectiontitle) {
        this.sectiontitle = sectiontitle;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.vid);
        dest.writeString(this.mp4Hd_url);
        dest.writeString(this.cover);
        dest.writeString(this.title);
        dest.writeString(this.sectiontitle);
        dest.writeString(this.mp4_url);
        dest.writeInt(this.length);
        dest.writeString(this.m3u8Hd_url);
        dest.writeString(this.ptime);
        dest.writeString(this.m3u8_url);
        dest.writeString(this.videoUrl);
        dest.writeLong(this.totalSize);
        dest.writeLong(this.loadedSize);
        dest.writeInt(this.downloadStatus);
        dest.writeLong(this.downloadSpeed);
       // dest.writeByte(this.isCollect ? (byte) 1 : (byte) 0);
    }

    protected VideoInfo(Parcel in) {
        this.vid = in.readString();
        this.mp4Hd_url = in.readString();
        this.cover = in.readString();
        this.title = in.readString();
        this.sectiontitle = in.readString();
        this.mp4_url = in.readString();
        this.length = in.readInt();
        this.m3u8Hd_url = in.readString();
        this.ptime = in.readString();
        this.m3u8_url = in.readString();
        this.videoUrl = in.readString();
        this.totalSize = in.readLong();
        this.loadedSize = in.readLong();
        this.downloadStatus = in.readInt();
        this.downloadSpeed = in.readLong();
    }

    //此为CREATOR方法，此方法名必须为CREATOR，不能为Creator
    public static final Parcelable.Creator<VideoInfo> CREATOR = new Creator<VideoInfo>() {

        @Override
        public VideoInfo createFromParcel(Parcel source) {
            return new VideoInfo(source);
        }

        @Override
        public VideoInfo[] newArray(int size) {
            return new VideoInfo[size];
        }
    };
    /*public static final Creator<VideoInfo> CREATOR = new Creator<VideoInfo>() {
        @Override
        public VideoInfo createFromParcel(Parcel source) {
            return new VideoInfo(source);
        }

        @Override
        public VideoInfo[] newArray(int size) {
            return new VideoInfo[size];
        }
    };*/

    public static class VideoTopicBean implements Serializable {
        /**
         * ename : T1498037795416
         * tname : 明星大剧抢先看
         * alias : 最新热剧、最火花絮、最多明星
         * topic_icons : http://dingyue.nosdn.127.net/5scL8DIaNxlw335R3hNvnCN306qL3mhJdUW9Y84XsGyUm1498037794827.jpg
         * tid : T1498037795416
         */

        private String ename;
        private String tname;
        private String alias;
        private String topic_icons;
        private String tid;

        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getTopic_icons() {
            return topic_icons;
        }

        public void setTopic_icons(String topic_icons) {
            this.topic_icons = topic_icons;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }
    }
}
