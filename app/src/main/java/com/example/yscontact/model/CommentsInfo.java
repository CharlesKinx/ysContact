package com.example.yscontact.model;

import java.io.Serializable;

public class CommentsInfo implements Serializable {
    private int commentInfoID = 0;
    private int forumID;
    private String comment;
    private String user;

    public int getForumID() {
        return forumID;
    }

    public void setForumID(int forumID) {
        this.forumID = forumID;
    }

    public int getCommentInfoID() {
        return commentInfoID;
    }

    public void setCommentInfoID(int commentInfoID) {
        this.commentInfoID = commentInfoID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CommentsInfo{" +
                "commentInfoID=" + commentInfoID +
                ", comment='" + comment + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
