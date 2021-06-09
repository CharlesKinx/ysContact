package com.example.yscontact.model;

import org.w3c.dom.Text;

import java.util.List;

public class ForumInfo {
    private UserInfo userInfo;
    private String title;
    private Text content;
    private List<CommentsInfo> commentsInfoList;
    private int comments = 0;

    public List<CommentsInfo> getCommentsInfoList() {
        return commentsInfoList;
    }

    public void setCommentsInfoList(List<CommentsInfo> commentsInfoList) {
        this.commentsInfoList = commentsInfoList;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Text getContent() {
        return content;
    }

    public void setContent(Text content) {
        this.content = content;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }
}
