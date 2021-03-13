package com.music_player.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.music_player.dao.UserTrackDAO;
import com.music_player.model.Track;

@Service(value = "userTrackService")
@Transactional
public class UserTrackServiceImpl implements UserTrackService {

	@Autowired
	private UserTrackDAO userTrackDAO;
	
	@Override
	public List<Track> getAllTracks(){
		List<Track> tracks = null;
		tracks = userTrackDAO.getAllTracks();
		return tracks;
	}
	
//	@Override
//	public List<Track> getLikedTracks(){
//		List<Track> likedTracks = null;
//		likedTracks = userTrackDAO.getLikedTracks();
//		return likedTracks;
//	}

}
