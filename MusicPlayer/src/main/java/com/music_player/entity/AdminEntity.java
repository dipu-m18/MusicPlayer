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
@Table(name="MP_ADMIN")
public class AdminEntity {
	@Id
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="ADMIN_EMAIL_ID")
	private List<TrackEntity> trackEntities;
	
	public List<TrackEntity> getTrackEntities() {
		return trackEntities;
	}

	public void setTrackEntities(List<TrackEntity> trackEntities) {
		this.trackEntities = trackEntities;
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
}
