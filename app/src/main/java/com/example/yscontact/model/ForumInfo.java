package com.example.yscontact.model;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

public class ForumInfo implements Serializable {
    private int forumID;
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

    public int getForumID() {
        return forumID;
    }

    public void setForumID(int forumID) {
        this.forumID = forumID;
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

    @Override
    public String toString() {
        return "ForumInfo{" +
                "forumID=" + forumID +
                ", userInfo=" + userInfo +
                ", title='" + title + '\'' +
                ", content=" + content +
                ", commentsInfoList=" + commentsInfoList +
                ", comments=" + comments +
                '}';
    }
}
