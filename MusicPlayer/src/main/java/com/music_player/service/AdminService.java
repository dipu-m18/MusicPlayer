package com.music_player.service;

import com.music_player.model.*;

public interface AdminService {
	
	public Admin authenticateAdmin(String emailId, String password) throws Exception;
	
	public String registerNewAdmin(Admin admin) throws Exception;
	
	public void updateProfile(Admin admin) throws Exception;
	
	public void changePassword(Admin admin) throws Exception;
}
