package com.example.smartcity_v2.beans;

import java.io.Serializable;

public class ItemNewsList implements Serializable {

    private int id;
    private String title;
    private String cover;
    private String content;
    private int commentNum;
    private int likeNum;
    private int readNum;
    private String publishDate;

    public ItemNewsList(int id, String title, String cover, String content, int commentNum, int likeNum, int readNum, String publishDate) {
        this.id = id;
        this.title = title;
        this.cover = cover;
        this.content = content;
        this.commentNum = commentNum;
        this.likeNum = likeNum;
        this.readNum = readNum;
        this.publishDate = publishDate;
    }

    public ItemNewsList() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public String toString() {
        return "ItemNewsList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
