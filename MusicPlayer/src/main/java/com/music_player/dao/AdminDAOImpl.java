package com.music_player.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.music_player.entity.AdminEntity;
import com.music_player.entity.TrackEntity;
import com.music_player.model.Admin;
import com.music_player.model.Track;
import com.music_player.utility.HashingUtility;


@Repository(value="adminDAO")
public class AdminDAOImpl implements AdminDAO{
		
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public String getPasswordOfAdmin(String emailId) {
		String password = null;
		AdminEntity adminEntity =entityManager.find(AdminEntity.class, emailId);
		if(adminEntity!=null) {
			password = adminEntity.getPassword();
		}
		return password;
	}
	
	@Override
	public Admin getAdminByEmailId(String emailId) throws Exception{
		List<Track> trackList =new ArrayList<>();
		AdminEntity adminEntity =entityManager.find(AdminEntity.class, emailId);
		Admin admin = new Admin();
		admin.setEmailId(adminEntity.getEmailId());
		admin.setName(adminEntity.getName());
		
		for(TrackEntity trackEntity: adminEntity.getTrackEntities()) {
			Track track = new Track();
			track.setTrackId(trackEntity.getTrackId());
			track.setName(trackEntity.getName());
			track.setTrackUrl(trackEntity.getTrackUrl());
			track.setImageUrl(trackEntity.getImageUrl());
			track.setArtists(trackEntity.getArtists());
			track.setGenre(trackEntity.getGenre());
			track.setPerformedBy(trackEntity.getPerformedBy());
			track.setProducedBy(trackEntity.getProducedBy());
			track.setWrittenBy(trackEntity.getWrittenBy());
			track.setSource(trackEntity.getSource());
			trackList.add(track);
			
		}
		admin.setTracks(trackList);
		
	return admin;
	}
	
	@Override
	public Boolean checkAvailabilityOfEmailId(String emailId) {
		Boolean flag=false;
		Query query = entityManager.createQuery("select a from AdminEntity a where a.emailId = :emailId");
		query.setParameter("emailId", emailId);
		List<AdminEntity> adminEntities = query.getResultList();
		if(adminEntities.isEmpty()) {
			//System.out.println(flag)
			flag=true;
		}System.out.println("Dao "+flag);
		return flag;
	}
	
	@Override
	public String registerNewAdmin(Admin admin) throws Exception{
		
		System.out.println("admin added");
		String registeredWithEmailId=null;
		AdminEntity adminEntity = new AdminEntity();
		adminEntity.setEmailId(admin.getEmailId());
		adminEntity.setName(admin.getName());
		adminEntity.setPassword(admin.getPassword());
		adminEntity.setTrackEntities(null);
		
		entityManager.persist(adminEntity);
		System.out.println("admin added");
		registeredWithEmailId = adminEntity.getEmailId();
		
		return registeredWithEmailId;
	}
	
	@Override
	public void updateProfile(Admin admin) {
		AdminEntity adminEntity = entityManager.find(AdminEntity.class, admin.getEmailId());
		adminEntity.setName(admin.getName());
	}
	
	@Override
	public void changePassword(String adminEmailId, String newHashedPassword) {
		
		AdminEntity adminEntity = entityManager.find(AdminEntity.class, adminEmailId);		
		adminEntity.setPassword(newHashedPassword); 
		
	}
	
}
