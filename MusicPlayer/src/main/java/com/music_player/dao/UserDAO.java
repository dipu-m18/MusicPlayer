package com.music_player.dao;

import com.music_player.model.User;

public interface UserDAO {
	
	public Boolean checkAvailabilityOfEmailId(String emailId);
	public Boolean checkAvailabilityOfPhoneNumber(String phoneNumber);
	public String registerNewUser(User user);
	public String authenticateUser(String emailId, String password);
	public String getPasswordOfUser(String emailId);
	public User getUserByEmailId(String emailId);
	public User getUserByPhoneNumber(String phoneNumber);
	public void updateProfile(User user);
	public void changePassword(String userEmailId, String newHashedPassword);
	
}
