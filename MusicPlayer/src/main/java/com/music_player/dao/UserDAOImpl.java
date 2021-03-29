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
	
	@Override
	public Boolean checkAvailabilityOfPhoneNumber(String phoneNumber) {
		Boolean flag= false;
		Query query = entityManager.createQuery("select u from UserEntity u where u.phoneNumber = :phoneNumber");
		query.setParameter("phoneNumber", phoneNumber);
		List<UserEntity> userEntities = query.getResultList();
		if(userEntities.isEmpty()) {
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
		userEntity.setLikedTracks(null);
		
		entityManager.persist(userEntity);
		
		registeredWithEmailId = userEntity.getEmailId();
		return registeredWithEmailId;
		
	}
	
	public String authenticateUser(String emailId, String password) {
		System.out.println("DAO ");
		Query query=entityManager.createQuery("select u from UserEntity u where u.emailId = :emailId and u.password = :password" );
		List<UserEntity> userEntities = query.getResultList();
		System.out.println("DAO "+userEntities);
		
		if(userEntities.isEmpty()) {
			System.out.println("DAO  null");
			return null;
		}
		
		System.out.println(userEntities.get(0).getEmailId());
		return userEntities.get(0).getEmailId();
	}
	public String getPasswordOfUser(String emailId) {
		String password = null;
		emailId = emailId.toLowerCase();
		UserEntity userEntity =entityManager.find(UserEntity.class, emailId);
		System.out.println("In dao "+userEntity);
		if(userEntity!=null) {
			password = userEntity.getPassword();
			System.out.println("in dao "+password);
		}
		return password;
	}
	public User getUserByEmailId(String emailId) {
		User user = null;
		emailId=emailId.toLowerCase();
		
		UserEntity userEntity = entityManager.find(UserEntity.class, emailId);
		if(userEntity!=null) {
			user = new User();
			
			user.setEmailId(userEntity.getEmailId());
			user.setName(userEntity.getName());
			user.setPassword(userEntity.getPassword());
			user.setPhoneNumber(userEntity.getPhoneNumber());
			List<LikedTrack> likedTrackList =new ArrayList<LikedTrack>();
			for(LikedTrackEntity likedTrackEntity:userEntity.getLikedTracks()) {
				Track track = new Track();
				LikedTrack likedTrack = new LikedTrack();
				track.setTrackId(likedTrackEntity.getTrackEntity().getTrackId());
				track.setName(likedTrackEntity.getTrackEntity().getName());
				track.setTrackUrl(likedTrackEntity.getTrackEntity().getTrackUrl());
				track.setImageUrl(likedTrackEntity.getTrackEntity().getImageUrl());
				track.setGenre(likedTrackEntity.getTrackEntity().getGenre());
				track.setPerformedBy(likedTrackEntity.getTrackEntity().getPerformedBy());
				track.setProducedBy(likedTrackEntity.getTrackEntity().getProducedBy());
				track.setWrittenBy(likedTrackEntity.getTrackEntity().getWrittenBy());
				track.setSource(likedTrackEntity.getTrackEntity().getSource());
				likedTrack.setTrack(track);
				likedTrackList.add(likedTrack);
			}
			user.setLikedTracks(likedTrackList);
		}
		return user;
	}
	
	@Override
	public User getUserByPhoneNumber(String phoneNumber) {
		UserEntity userEntity=null;
		User user = null;
		
		
		Query query = entityManager.createQuery("select u from UserEntity u where u.phoneNumber = :phoneNumber");
		query.setParameter("phoneNumber", phoneNumber);
		List<UserEntity> userEntities =query.getResultList();
		List<LikedTrack> likedTrackList =new ArrayList<LikedTrack>();
		if(!userEntities.isEmpty()) {
			 userEntity = userEntities.get(0);
			 if(userEntity!=null) {
					user = new User();
					user.setEmailId(userEntity.getEmailId());
					user.setName(userEntity.getName());
					user.setPassword(userEntity.getPassword());
					user.setPhoneNumber(userEntity.getPhoneNumber());
					for(LikedTrackEntity likedTrackEntity:userEntity.getLikedTracks()) {
						Track track = new Track();
						LikedTrack likedTrack = new LikedTrack();
						track.setTrackId(likedTrackEntity.getTrackEntity().getTrackId());
						track.setName(likedTrackEntity.getTrackEntity().getName());
						track.setTrackUrl(likedTrackEntity.getTrackEntity().getTrackUrl());
						track.setImageUrl(likedTrackEntity.getTrackEntity().getImageUrl());
						track.setGenre(likedTrackEntity.getTrackEntity().getGenre());
						track.setPerformedBy(likedTrackEntity.getTrackEntity().getPerformedBy());
						track.setProducedBy(likedTrackEntity.getTrackEntity().getProducedBy());
						track.setWrittenBy(likedTrackEntity.getTrackEntity().getWrittenBy());
						track.setSource(likedTrackEntity.getTrackEntity().getSource());
						likedTrack.setTrack(track);
						likedTrackList.add(likedTrack);
					}
					user.setLikedTracks(likedTrackList);
				}
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
