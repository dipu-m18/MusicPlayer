package com.music_player.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.music_player.entity.AdminEntity;
import com.music_player.entity.TrackEntity;
import com.music_player.entity.TrackGenreEntity;
import com.music_player.model.Track;

@Repository(value = "adminTrackDAO")
public class AdminTrackDAOImpl implements AdminTrackDAO{

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Integer addNewTrack(Track track) {
		
		TrackEntity newTrack = new TrackEntity();
		newTrack.setTrackUrl(track.getTrackUrl());
		newTrack.setName(track.getName());
		newTrack.setAdminEmailId(track.getAdminEmailId());
		newTrack.setArtists(track.getArtists());
		newTrack.setGenre(track.getGenre());
		newTrack.setImageUrl(track.getImageUrl());
		newTrack.setPerformedBy(track.getPerformedBy());
		newTrack.setProducedBy(track.getProducedBy());
		newTrack.setSource(track.getSource());
		newTrack.setWrittenBy(track.getWrittenBy());
		
		AdminEntity adminEntity = entityManager.find(AdminEntity.class, track.getAdminEmailId());
		adminEntity.getTrackEntities().add(newTrack);
		entityManager.persist(adminEntity);
		
		TrackEntity lastTrack = adminEntity.getTrackEntities().get(adminEntity.getTrackEntities().size()-1);
		return lastTrack.getTrackId();
	}
	
	@Override
	public Track modifyTrackDetails(Track track) {
		
		Query query = entityManager.createQuery("select t from TrackEntity t where t.trackId = :trackId");
		query.setParameter("trackId", track.getTrackId());
		TrackEntity trackEntity = (TrackEntity) query.getResultList().get(0);
		trackEntity.setAdminEmailId(track.getAdminEmailId());
		trackEntity.setArtists(track.getArtists());
		trackEntity.setGenre(track.getGenre());
		trackEntity.setImageUrl(track.getImageUrl());
		trackEntity.setName(track.getName());
		trackEntity.setPerformedBy(track.getPerformedBy());
		trackEntity.setProducedBy(track.getProducedBy());
		trackEntity.setSource(track.getSource());
		trackEntity.setTrackId(track.getTrackId());
		trackEntity.setTrackUrl(track.getTrackUrl());
		trackEntity.setWrittenBy(track.getWrittenBy());
	
		return track;
	}
	
	@Override
	public Integer removeTrack(String adminEmailId, Integer trackId) {
		AdminEntity adminEntity = entityManager.find(AdminEntity.class, adminEmailId);
		List<TrackEntity> trackEntities = adminEntity.getTrackEntities();
		List<TrackEntity> updatedTrackEntities = new ArrayList<>();
		for(TrackEntity trackEntity: trackEntities) {
			if(!trackId.equals(trackEntity.getTrackId())) {
				updatedTrackEntities.add(trackEntity);
			}
		}
		
		adminEntity.setTrackEntities(updatedTrackEntities);
		return trackId;
	}
	
	@Override
	public List<Track> getTrackList(String emailId){
		AdminEntity adminEntity = entityManager.find(AdminEntity.class, emailId);
		List<TrackEntity>trackEntityList = adminEntity.getTrackEntities();
		List<Track> trackList = new ArrayList<Track>();
		for(TrackEntity  trackEntity: trackEntityList) {
			Track track = new Track();
			track.setTrackId(trackEntity.getTrackId());
			track.setTrackUrl(trackEntity.getTrackUrl());
			track.setName(trackEntity.getName());
			track.setAdminEmailId(trackEntity.getAdminEmailId());
			track.setArtists(trackEntity.getArtists());
			track.setGenre(trackEntity.getGenre());
			track.setImageUrl(trackEntity.getImageUrl());
			track.setPerformedBy(trackEntity.getPerformedBy());
			track.setProducedBy(trackEntity.getProducedBy());
			track.setSource(trackEntity.getSource());
			track.setWrittenBy(trackEntity.getWrittenBy());
			
			trackList.add(track);
		}
		return trackList;
	}
	
	@Override
	public List<String> getTrackGenreList()
	{
		Query query = entityManager.createQuery("select t from TrackGenreEntity t");
		List<TrackGenreEntity> trackGenreEntityList = query.getResultList();
		
		List<String> trackGenres = new ArrayList<>();
		for(TrackGenreEntity trackGenreEntity: trackGenreEntityList) {
			trackGenres.add(trackGenreEntity.getGenre());
		}
		
		return trackGenres;
	}
	
}

