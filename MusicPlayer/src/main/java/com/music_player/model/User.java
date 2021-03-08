package com.music_player.model;

import java.util.List;

public class User {
	
	private String emailId;
	private String name;
	private String password;
	private String newPassword;
	private String phoneNumber;
	

	private List<Track> likedTracks;
	
	private String errorMessage;
	
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public List<Track> getLikedTracks() {
		return likedTracks;
	}

	public void setLikedTracks(List<Track> likedTracks) {
		this.likedTracks = likedTracks;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
