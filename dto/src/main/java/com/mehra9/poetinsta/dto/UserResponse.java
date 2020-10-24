package com.mehra9.poetinsta.dto;

import java.util.List;

public class UserResponse {

    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private List<ActivityResponse> activities;
    private List<PoemResponse> poems;
    private byte[] picByte;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<ActivityResponse> getActivities() {
        return activities;
    }
    public void setActivities(List<ActivityResponse> activities) {
        this.activities = activities;
    }
    public List<PoemResponse> getPoems() {
        return poems;
    }
    public void setPoems(List<PoemResponse> poems) {
        this.poems = poems;
    }
	public byte[] getPicByte() {
		return picByte;
	}
	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}
    
}
