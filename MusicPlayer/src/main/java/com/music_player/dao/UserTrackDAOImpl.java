package com.music_player.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.music_player.entity.AdminEntity;
import com.music_player.entity.LikedTrackEntity;
import com.music_player.entity.TrackEntity;
import com.music_player.entity.UserEntity;
import com.music_player.model.LikedTrack;
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
	
	
	@Override
	public List<LikedTrack> getLikedTracks(String userEmailId){
		UserEntity userEntity = entityManager.find(UserEntity.class, userEmailId);
		List<LikedTrackEntity>likeTrackEntityList = userEntity.getLikedTracks();
		List<LikedTrack> likedTrackList = new ArrayList<LikedTrack>();
		for(LikedTrackEntity  likedTrackEntity: likeTrackEntityList) {
			Track track = new Track();
			LikedTrack likedTrack = new LikedTrack();
			track.setTrackId(likedTrackEntity.getTrackEntity().getTrackId());
			track.setTrackUrl(likedTrackEntity.getTrackEntity().getTrackUrl());
			track.setName(likedTrackEntity.getTrackEntity().getName());
			track.setAdminEmailId(likedTrackEntity.getTrackEntity().getAdminEmailId());
			track.setGenre(likedTrackEntity.getTrackEntity().getGenre());
			track.setImageUrl(likedTrackEntity.getTrackEntity().getImageUrl());
			track.setPerformedBy(likedTrackEntity.getTrackEntity().getPerformedBy());
			track.setProducedBy(likedTrackEntity.getTrackEntity().getProducedBy());
			track.setSource(likedTrackEntity.getTrackEntity().getSource());
			track.setWrittenBy(likedTrackEntity.getTrackEntity().getWrittenBy());
			likedTrack.setTrack(track);
			likedTrackEntity.setLikedTrackId(track.getTrackId());
//			likedTrack.setTrackId(track.getTrackId());
			likedTrack.setUserEmailId(userEmailId);
			likedTrack.setLikedTrackId(track.getTrackId());
			likedTrackList.add(likedTrack);
		}
		return likedTrackList;
	}
	
	@Override
	public Integer addLikedTrack(LikedTrack likedTrack)throws Exception {
		
		Boolean flag = false;
		Integer trackId = likedTrack.getLikedTrackId();
		Query query = entityManager.createQuery("Select t from LikedTrackEntity t where t.likedTrackId = :trackId");
		query.setParameter("trackId", trackId);
		List<LikedTrackEntity> likedEntities = query.getResultList();
		if(likedEntities.isEmpty()) {
			flag= true;
			LikedTrackEntity likedTrackEntity = new LikedTrackEntity();
			Track track = likedTrack.getTrack();
			TrackEntity trackEntity =new TrackEntity();
			trackEntity.setTrackId(track.getTrackId());
			trackEntity.setTrackUrl(track.getTrackUrl());
			trackEntity.setWrittenBy(track.getWrittenBy());
			trackEntity.setSource(track.getSource());
			trackEntity.setProducedBy(track.getProducedBy());
			trackEntity.setPerformedBy(track.getPerformedBy());
			trackEntity.setName(track.getName());
			trackEntity.setImageUrl(track.getImageUrl());
			trackEntity.setGenre(track.getGenre());
			likedTrackEntity.setTrackEntity(trackEntity);
			likedTrackEntity.setLikedTrackId(trackEntity.getTrackId());
			likedTrackEntity.setUserEmailId(likedTrack.getUserEmailId());
			likedTrackEntity.setLiked(true);
			
			UserEntity userEntity = entityManager.find(UserEntity.class, likedTrack.getUserEmailId());
			userEntity.getLikedTracks().add(likedTrackEntity);
			entityManager.persist(userEntity);
			return likedTrackEntity.getLikedTrackId();
		}
		throw new Exception("UserDAO.TRACK_ALREADY_LIKED");	
	}
}
