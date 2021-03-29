package com.music_player.service;

import java.util.List;

import com.music_player.model.LikedTrack;
import com.music_player.model.Track;

public interface UserTrackService {

	public List<Track> getAllTracks();
	public List<LikedTrack> getLikedTracks(String userEmailId);
	public Integer addLikedTrack(LikedTrack likedTrack) throws Exception;
}
