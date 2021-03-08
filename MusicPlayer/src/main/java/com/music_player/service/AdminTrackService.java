package com.music_player.service;

import java.util.List;

import com.music_player.model.Track;

public interface AdminTrackService {
	
	public Integer addNewTrack(Track track) throws Exception;
	public Track modifyTrackDetails(Track track) throws Exception;
	public Integer removeTrack(Track track) throws Exception;
	public List<Track> getTrackList(String emailId) throws Exception;
	public List<String> getTrackGenreList() throws Exception;
}
