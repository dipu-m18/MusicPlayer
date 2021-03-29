package com.music_player.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.music_player.model.Track;

@Entity
@Table(name="MP_USER")
public class UserEntity {

	@Id
	@Column(name="EMAIL_ID")
	private String emailId;

	@Column(name="NAME")
	private String name;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="USER_EMAIL_ID")
	private List<LikedTrackEntity> likedTracks;
	

	public List<LikedTrackEntity> getLikedTracks() {
		return likedTracks;
	}

	public void setLikedTracks(List<LikedTrackEntity> likedTracks) {
		this.likedTracks = likedTracks;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	
}
