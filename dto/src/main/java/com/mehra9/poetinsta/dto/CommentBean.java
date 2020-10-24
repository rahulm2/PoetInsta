package com.mehra9.poetinsta.dto;

public class CommentBean {

    private Long poemId ;
    private Long userId;
    private String content;
    public Long getPoemId() {
        return poemId;
    }
    public void setPoemId(Long poemId) {
        this.poemId = poemId;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    
}
