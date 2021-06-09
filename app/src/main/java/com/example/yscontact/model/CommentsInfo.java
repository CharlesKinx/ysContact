package com.example.yscontact.model;

public class CommentsInfo {
    private int commentInfoID;
    private String comment;
    private UserInfo user;
    private ForumInfo forumInfo;

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

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }

    public ForumInfo getForumInfo() {
        return forumInfo;
    }

    public void setForumInfo(ForumInfo forumInfo) {
        this.forumInfo = forumInfo;
    }
}
