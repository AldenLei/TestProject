package com.qf.administrator.baozou.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by Alden on 2017/5/10.
 */

@Entity
public class DataBaseEntity implements Serializable{
    @Id(autoincrement = true)
    private Long id;
    private String document_id;
    private int display_type;
    private String title;//标题
    private String iamge;//缩略图，前面的,投稿没得
    private String url;//视频是file_url,投稿没得，文章有
    private String share_url;
    private String timestamp;//时间戳，投稿和视频是发布时间，而且多了三个0
    private String play_count;//播放次数，只有视频有
    private String comment_count;
    private String vote_count;
    private String section_name;//文档才有分类
    private String author_id;//视频没得
    private String author_name;//视频没得
    private String author_avatar;//视频没得
    private String readFlag;
    public String getAuthor_avatar() {
        return this.author_avatar;
    }
    public void setAuthor_avatar(String author_avatar) {
        this.author_avatar = author_avatar;
    }
    public String getAuthor_name() {
        return this.author_name;
    }
    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
    public String getAuthor_id() {
        return this.author_id;
    }
    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }
    public String getSection_name() {
        return this.section_name;
    }
    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }
    public String getVote_count() {
        return this.vote_count;
    }
    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }
    public String getComment_count() {
        return this.comment_count;
    }
    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }
    public String getPlay_count() {
        return this.play_count;
    }
    public void setPlay_count(String play_count) {
        this.play_count = play_count;
    }
    public String getTimestamp() {
        return this.timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public String getShare_url() {
        return this.share_url;
    }
    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getIamge() {
        return this.iamge;
    }
    public void setIamge(String iamge) {
        this.iamge = iamge;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getDisplay_type() {
        return this.display_type;
    }
    public void setDisplay_type(int display_type) {
        this.display_type = display_type;
    }
    public String getDocument_id() {
        return this.document_id;
    }
    public void setDocument_id(String document_id) {
        this.document_id = document_id;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getReadFlag() {
        return this.readFlag;
    }
    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag;
    }

    @Generated(hash = 697849300)
    public DataBaseEntity(Long id, String document_id, int display_type,
            String title, String iamge, String url, String share_url,
            String timestamp, String play_count, String comment_count,
            String vote_count, String section_name, String author_id,
            String author_name, String author_avatar, String readFlag) {
        this.id = id;
        this.document_id = document_id;
        this.display_type = display_type;
        this.title = title;
        this.iamge = iamge;
        this.url = url;
        this.share_url = share_url;
        this.timestamp = timestamp;
        this.play_count = play_count;
        this.comment_count = comment_count;
        this.vote_count = vote_count;
        this.section_name = section_name;
        this.author_id = author_id;
        this.author_name = author_name;
        this.author_avatar = author_avatar;
        this.readFlag = readFlag;
    }
    @Generated(hash = 2126908982)
    public DataBaseEntity() {
    }

}
