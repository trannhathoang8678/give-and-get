package fpt.se1601.giveandget.controller.response;

import fpt.se1601.giveandget.reponsitory.entity.CommentEntity;

public class CommentResponse {

    private CommentEntity commentEntity;
    private String userName;

    public CommentResponse() {
    }

    public CommentResponse(CommentEntity commentEntity, String userName) {
        this.userName = userName;
        this.commentEntity = commentEntity;
    }

    public CommentEntity getCommentEntity() {
        return commentEntity;
    }

    public void setCommentEntity(CommentEntity commentEntity) {
        this.commentEntity = commentEntity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
