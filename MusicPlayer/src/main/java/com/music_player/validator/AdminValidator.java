package com.music_player.validator;

import com.music_player.model.Admin;

public class AdminValidator {
	
	public static void validateAdminForUpdateProfile(Admin admin) throws Exception{
		
		if(!validateName(admin.getName())) {
			throw new Exception("AdminValidator.INVALID_NAME");
		}
	}
	
	public static void validateAdminForLogin(String emailId, String password) throws Exception{
		if(!validateEmailId(emailId)) {
			throw new Exception("AdminValidator.INVALID_EMAIL_FORMAT_FOR_LOGIN");
		}
		if (!validatePassword(password)) {
			throw new Exception("AdminValidator.INVALID_PASSWORD_FORMAT_FOR_LOGIN");
		}
	}
	
	public static void validateAdminForRegistration(Admin admin) throws Exception{
		if(!validateEmailId(admin.getEmailId())) {
			throw new Exception("SellerValidator.INVALID_EMAIL_FORMAT");
		}
		
		if(!validateName(admin.getName())) {
			throw new Exception("AdminValidator.INVALID_NAME");
		}
		
		if(!validatePassword(admin.getPassword())) {
			throw new Exception("AdminValidator.INVALID_PASSWORD_FORMAT");
		}
	}
	
	public static void validatePasswordForAdminChangePassword(Admin admin) throws Exception{
		if(!validatePassword(admin.getPassword())) {
			throw new Exception("AdminValidator.INVALID_CURRENT_PASSWORD_FOR_CHANGE_PASSWORD");
		}
		if(!validatePassword(admin.getNewPassword())) {
			throw new Exception("SellerValidator.INVALID_NEW_PASSWORD");
		}
		if(!admin.getNewPassword().equals(admin.getConfirmNewPassword())) {
			throw new Exception("SellerValidator.NEW_PASSWORD_AND_CONFIRM_NEW_PASSWORD_MISMATCH");
		}
	}
	
	public static Boolean validateName(String name) {
		Boolean flag = false;
		if(!name.matches("[ ]*") && name.matches("([A-Za-z])+(\\s[A-Za-z]+)*")) {
			flag=true;
		}
		return flag;
	}
	
	public static Boolean validateEmailId(String emailId) {
		Boolean flag = false;
		if(emailId.matches("[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+")) {
			flag=true;
		}
		return flag;
	}
	
	public static Boolean validatePassword(String password) {
		Boolean flag = false;
		if(password.length()>=6 && password.length()<=20) {
			if(password.matches(".*[A-Z]+.*")) {
				if(password.matches(".*[0-9]+.*")) {
					if(password.matches(".*[^a-zA-Z0-9].*")) {
						flag=true;
					}
				}
			}
		}
		return flag;
	}
	
}
