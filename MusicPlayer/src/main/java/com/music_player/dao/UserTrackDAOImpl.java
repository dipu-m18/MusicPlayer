package com.music_player.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.music_player.entity.TrackEntity;
import com.music_player.model.Track;


@Repository(value="")
public class UserTrackDAOImpl {
	

@Autowired
private EntityManager entityManager;

	public List<Track> getAllTracks(){
		Query query = entityManager.createQuery("select t from TrackEntity t");
		List<TrackEntity> trackEntityList =query.getResultList();
		List<Track> listOfTracks =new ArrayList<Track>();
		for(TrackEntity trackEntity: trackEntityList) {
			Track track = new Track();
			track.setAdminEmailId(trackEntity.getAdminEmailId());
			track.setArtists(trackEntity.getArtists());
			track.setGenre(trackEntity.getGenre());
			track.setImageUrl(trackEntity.getImageUrl());
			track.setName(trackEntity.getName());
			track.setPerformedBy(trackEntity.getPerformedBy());
			track.setProducedBy(trackEntity.getProducedBy());
			track.setSource(trackEntity.getSource());
			track.setTrackId(trackEntity.getTrackId());
			track.setTrackUrl(trackEntity.getTrackUrl());
			track.setWrittenBy(trackEntity.getWrittenBy());
		
			listOfTracks.add(track);
		}
		return listOfTracks;
	}
	
	public List<Track> getLikedTracks(){
		Query query = entityManager.createQuery("select l from likedTrackEntity l");
		List<TrackEntity> likedTrackEntities =query.getResultList();
		List<Track> likedTracks = new ArrayList<Track>();
		
		for(TrackEntity likedTrackEntity: likedTrackEntities) {
			Track likedTrack = new Track();
			likedTrack.setAdminEmailId(likedTrackEntity.getAdminEmailId());
			likedTrack.setArtists(likedTrackEntity.getArtists());
			likedTrack.setGenre(likedTrackEntity.getGenre());
			likedTrack.setImageUrl(likedTrackEntity.getImageUrl());
			likedTrack.setName(likedTrackEntity.getName());
			likedTrack.setPerformedBy(likedTrackEntity.getPerformedBy());
			likedTrack.setProducedBy(likedTrackEntity.getProducedBy());
			likedTrack.setSource(likedTrackEntity.getSource());
			likedTrack.setTrackId(likedTrackEntity.getTrackId());
			likedTrack.setTrackUrl(likedTrackEntity.getTrackUrl());
			likedTrack.setWrittenBy(likedTrackEntity.getWrittenBy());
			
			likedTracks.add(likedTrack);
		}
		return likedTracks;
	}
}
