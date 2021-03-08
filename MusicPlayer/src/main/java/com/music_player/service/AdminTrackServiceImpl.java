package com.music_player.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.music_player.dao.AdminTrackDAO;
import com.music_player.entity.AdminEntity;
import com.music_player.entity.TrackEntity;
import com.music_player.model.Track;

@Service(value="adminTrackService")
@Transactional
public class AdminTrackServiceImpl implements AdminTrackService{
	
	@Autowired
	private AdminTrackDAO adminTrackDAO;
	
	public Integer addNewTrack(Track track) throws Exception{
		//AdminTrackValidator.validateTrackForAddNewTrack(track);
		Integer trackId = adminTrackDAO.addNewTrack(track);
		return trackId;
	}
	
	public Track modifyTrackDetails(Track trackForModification) throws Exception{
		//AdminTrackValidator.validateTrackForModifyTrackDetails(tackForModification);
		Track track = adminTrackDAO.modifyTrackDetails(trackForModification);
		return track;	
	}
	
	@Override
	public Integer removeTrack(Track track) throws Exception{
		return adminTrackDAO.removeTrack(track.getAdminEmailId(), track.getTrackId());
	}
	
	@Override
	public List<Track> getTrackList(String emailId) throws Exception{	
		return adminTrackDAO.getTrackList(emailId);
	}
	
	@Override
	public List<String> getTrackGenreList() throws Exception{
		return adminTrackDAO.getTrackGenreList();
	}
	

}
