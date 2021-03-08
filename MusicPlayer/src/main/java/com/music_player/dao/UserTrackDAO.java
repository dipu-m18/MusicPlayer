package com.music_player.dao;

import java.util.List;

import com.music_player.model.Track;

public interface UserTrackDAO {

	public List<Track> getAllTracks();
	public List<Track> getLikedTracks();
}
