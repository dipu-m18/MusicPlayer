package com.music_player.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.music_player.entity.TrackEntity;
import com.music_player.entity.UserEntity;
import com.music_player.model.Track;
import com.music_player.model.User;

@Repository(value="userDAO")
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Boolean checkAvailabilityOfEmailId(String emailId) {
		Boolean flag = false;
		UserEntity userEntity = null;
		userEntity = entityManager.find(UserEntity.class, emailId);
		if(userEntity == null) {
			flag=true;
		}
		return flag;
	}
	
	public String registerNewUser(User user) {
		String registeredWithEmailId = null;
		UserEntity userEntity =new UserEntity();
		userEntity.setEmailId(user.getEmailId());
		userEntity.setName(user.getName());
		userEntity.setPassword(user.getPassword());
		userEntity.setPhoneNumber(user.getPhoneNumber());
		
		entityManager.persist(userEntity);
		
		registeredWithEmailId = userEntity.getEmailId();
		return registeredWithEmailId;
		
	}
	
	public String authenticateUser(String emailId, String password) {
		Query query=entityManager.createQuery("select u from UserEntity u where u.emailId = "+emailId+" and u.password = "+password+" ");
		List<UserEntity> userEntities = query.getResultList();
		if(userEntities.isEmpty()) {
			return null;
		}
		
		return userEntities.get(0).getEmailId();
	}
	public String getPasswordOfUser(String emailId) {
		String password = null;
		emailId = emailId.toLowerCase();
		UserEntity userEntity =entityManager.find(UserEntity.class, emailId);
		if(userEntity!=null) {
			password = userEntity.getPassword();
		}
		return password;
	}
	public User getUserByEmailId(String emailId) {
		User user = null;
		emailId=emailId.toLowerCase();
		List<Track> likedTracks = new ArrayList<>();
		
		UserEntity userEntity = entityManager.find(UserEntity.class, emailId);
		if(userEntity!=null) {
			user = new User();
			user.setEmailId(userEntity.getEmailId());
			user.setName(userEntity.getName());
			user.setPassword(userEntity.getPassword());
			user.setPhoneNumber(userEntity.getPhoneNumber());
			for(TrackEntity likedTrackEntity: userEntity.getLikedTrackEntities()) {
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
			user.setLikedTracks(likedTracks);
		}
		return user;
		
	}
	
	public void updateProfile(User user) {
		UserEntity userEntity = entityManager.find(UserEntity.class, user.getEmailId().toLowerCase());
		userEntity.setName(user.getName());
		userEntity.setPhoneNumber(user.getPhoneNumber());
	}
	
	public void changePassword(String userEmailId, String newHashedPassword) {
		UserEntity userEntity = entityManager.find(UserEntity.class, userEmailId);
		userEntity.setPassword(newHashedPassword);
	}
	
}
