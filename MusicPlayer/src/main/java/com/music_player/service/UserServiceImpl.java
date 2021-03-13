package com.music_player.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.music_player.dao.UserDAO;
import com.music_player.model.User;
import com.music_player.utility.HashingUtility;

@Service( value = "userService" )
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public User authenticateUser(String emailId, String password) throws Exception{
//		User user = null;
//		System.out.println("Service ");
//		String userEmailIdFromDAO = userDAO.authenticateUser(emailId.toLowerCase(),password);
//		System.out.println("Service "+userEmailIdFromDAO);
//		if(userEmailIdFromDAO!=null) {
//			user = userDAO.getUserByEmailId(userEmailIdFromDAO);
//			System.out.println("Service "+user);
//		}else {
//			throw new Exception("UserService.INVALID_CREDENTIALS");
//		}
//		return user;
		
		User user = null;
		emailId =emailId.toLowerCase();
		System.out.println("inside authenticate User");
		String passwordFromDB = userDAO.getPasswordOfUser(emailId);
		if(passwordFromDB!=null) {
			System.out.println("password "+password);
		System.out.println("password "+passwordFromDB);
//			
//			String hashedPassword = HashingUtility.getHashValue(password);
//			System.out.println(hashedPassword);
			boolean flag=password.equals(passwordFromDB);
			System.out.println(flag);
			System.out.println(HashingUtility.getHashValue("abc"));
			System.out.println(HashingUtility.getHashValue("abc"));
			
			if(flag) {
				user = userDAO.getUserByEmailId(emailId);
			}
			else {
				throw new Exception("UserService.INVALID_CREDENTIALS");
			}
		}else {
			throw new Exception ("UserService.INVALID_CREDENTIALS");
		}
		return user;
	}
	
	@Override
	public String registerNewUser(User user) throws Exception{
		String registeredWithEmailId = null;
		//UserValidator.validateUser(user);
		Boolean emailAvailable = userDAO.checkAvailabilityOfEmailId(user.getEmailId());
		Boolean phoneNumberAvailable = userDAO.checkAvailabilityOfPhoneNumber(user.getPhoneNumber());
		if(emailAvailable) {
			if(phoneNumberAvailable) {
	
				String emailIdToDB = user.getEmailId().toLowerCase();
				user.setEmailId(emailIdToDB);
				registeredWithEmailId = userDAO.registerNewUser(user);
			}else {
				throw new Exception("UserService.PHONE_NUMBER_ALREADY_IN_USE");
			}
		}
		else {
			throw new Exception("UserService.EMAIL_ID_ALREADY_IN_USE");
		}
		return registeredWithEmailId;
	}

	@Override
	public void updateProfile(User user) throws Exception{
		User newUser = null;
		//UserValidator.validateUserForUpdateProfile(user
		
		newUser = userDAO.getUserByPhoneNumber(user.getPhoneNumber());
		if(newUser==null) {
			userDAO.updateProfile(user);
		}
		else {
			if(newUser.getEmailId().equals(user.getEmailId())) {
				userDAO.updateProfile(user);
			}
			else {
				throw new Exception("UserService.PHONE_NUMBER_ALREADY_IN_USE");
			}
		}
	}

	public void changePassword(String userEmailId, String currentPassword, String newPassword) throws Exception{
		//Boolean validPassword=UserValidator.validatePassword(newPassword);
//		if(!validPassword) {
//			throw new Exception("UserService.INVALID_NEW_PASSWORD");
//		}
		
		String passwordFromDB= userDAO.getPasswordOfUser(userEmailId);
		if(!passwordFromDB.equals(currentPassword)) {
			throw new Exception("UserService.INVALID_CURRENT_PASSWORD");
		}
		
		if(!currentPassword.equals(newPassword)) {
			throw new Exception("UserService.OLD_PASSWORD_NEW_PASSWORD_SAME");
		}
		
		userDAO.changePassword(userEmailId, newPassword);
	}

}
