package com.mehra9.poetinsta.dto;

import java.util.ArrayList;
import java.util.List;


public class PoemResponse {

    private Long id;

    private String author;
    private String title;
    private int rating;
    private String content;
    private List<CommentResponse> comments = new ArrayList<>(0);
	private byte[] picByte;

    public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<CommentResponse> getComments() {
        return comments;
    }

    public void setComments(List<CommentResponse> commentResponses) {
        this.comments = commentResponses;
    }



}
