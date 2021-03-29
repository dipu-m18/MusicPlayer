package com.music_player.model;

public class LikedTrack {
	private Integer likedTrackId;
	private String userEmailId;
//	private Integer trackId;	
	private Track track;
	private Boolean liked;
	private String errorMessage;
	private String successMessgae;
	
	public Integer getLikedTrackId() {
		return likedTrackId;
	}
	public void setLikedTrackId(Integer likedTrackId) {
		this.likedTrackId = likedTrackId;
	}
	
//	public Integer getTrackId() {
//		return trackId;
//	}
//	public void setTrackId(Integer trackId) {
//		this.trackId = trackId;
//	}
//	
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public Track getTrack() {
		return track;
	}
	public void setTrack(Track track) {
		this.track = track;
	}
	
	public Boolean getLiked() {
		return liked;
	}
	public void setLiked(Boolean liked) {
		this.liked = liked;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getSuccessMessgae() {
		return successMessgae;
	}
	public void setSuccessMessgae(String successMessgae) {
		this.successMessgae = successMessgae;
	}
	
}
