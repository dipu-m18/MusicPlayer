package com.music_player.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MP_CUSTOMER")
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
	@JoinColumn(name="ADMIN_EMAIL_ID")
	private List<TrackEntity> likedTrackEntities;
	
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

	public List<TrackEntity> getLikedTrackEntities() {
		return likedTrackEntities;
	}

	public void setLikedTrackEntities(List<TrackEntity> likedTrackEntities) {
		this.likedTrackEntities = likedTrackEntities;
	}
}