package com.music_player.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="MP_LIKED_TRACK")
public class LikedTrackEntity {
	
	@Id 
	@Column(name="LIKED_TRACK_ID")
	private Integer likedTrackId;
	
	@Column(name="USER_EMAIL_ID")
	private String userEmailId;
	
	@Column(name="LIKED")
	private Boolean liked;
	
//	@Column(name="TRACKK_ID")
//	private Integer trackId;

	@OneToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="TRACK_ID")
	private TrackEntity trackEntity;
	
	public Integer getLikedTrackId() {
		return likedTrackId;
	}

	public void setLikedTrackId(Integer likedTrackId) {
		this.likedTrackId = likedTrackId;
	}

	public String getUserEmailId() {
		return userEmailId;
	}

	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}

//	public Integer getTrackId() {
//		return trackId;
//	}
//
//	public void setTrackId(Integer trackId) {
//		this.trackId = trackId;
//	}
	
	public Boolean getLiked() {
		return liked;
	}

	public void setLiked(Boolean liked) {
		this.liked = liked;
	}

	public TrackEntity getTrackEntity() {
		return trackEntity;
	}

	public void setTrackEntity(TrackEntity trackEntity) {
		this.trackEntity = trackEntity;
	}

	
	
}
