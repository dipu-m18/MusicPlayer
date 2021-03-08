package com.music_player.dao;

import java.util.List;

import com.music_player.model.Track;

public interface AdminTrackDAO {
	
	public Integer addNewTrack(Track track);
	
	public Track modifyTrackDetails(Track track);
	
	public Integer removeTrack(String adminEmailId, Integer trackId);
	
	public List<Track> getTrackList(String emailId);
	
	public List<String> getTrackGenreList();
	
}
