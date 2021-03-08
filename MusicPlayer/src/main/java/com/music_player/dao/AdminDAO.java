package com.music_player.dao;

import com.music_player.model.Admin;

public interface AdminDAO {

	public String getPasswordOfAdmin(String emailId);

	public Admin getAdminByEmailId(String emailId) throws Exception;
	
	public Boolean checkAvailabilityOfEmailId(String emailId);
	
	public String registerNewAdmin(Admin admin)throws Exception;
	
	public void updateProfile(Admin admin);
	
	public void changePassword(String adminEmailId, String newHashedPassword);

}
