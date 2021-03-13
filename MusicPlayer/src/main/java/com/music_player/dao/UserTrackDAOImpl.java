package com.music_player.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.music_player.entity.TrackEntity;
import com.music_player.model.Track;


@Repository(value="userTrackDAO")
public class UserTrackDAOImpl implements UserTrackDAO{
	

@Autowired
private EntityManager entityManager;

	@Override
	public List<Track> getAllTracks(){
		Query query = entityManager.createQuery("select t from TrackEntity t");
		List<TrackEntity> trackEntityList =query.getResultList();
		List<Track> listOfTracks =new ArrayList<Track>();
		System.out.println("In DAO "+listOfTracks.size());
		for(TrackEntity trackEntity: trackEntityList) {
			Track track = new Track();
			track.setAdminEmailId(trackEntity.getAdminEmailId());
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
	
	
}
