package com.music_player.service;

import com.music_player.model.User;

public interface UserService {
	
	public User authenticateUser(String emailId, String password) throws Exception;
	public String registerNewUser(User user) throws Exception ;

	public void updateProfile(User user) throws Exception;

	public void changePassword(String userEmailId, String currentPassword, String newPassword) throws Exception;
	
}
