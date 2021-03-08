package com.music_player.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.music_player.utility.HashingUtility;
import com.music_player.validator.AdminValidator;
import com.music_player.dao.AdminDAO;
import com.music_player.model.Admin;

@Service( value = "adminService" )
@Transactional
public class AdminServiceImp implements AdminService{

	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	public Admin authenticateAdmin(String emailId, String password)throws Exception{
		Admin admin = null;
		emailId = emailId.toLowerCase();
		//AdminValidator.validateAdminForLogin(emailId, password);
		System.out.println("inside authenticate Admin");
		String passwordFromDB = adminDAO.getPasswordOfAdmin(emailId);
		if(passwordFromDB!=null) {
			System.out.println("password "+password);
			System.out.println("password "+passwordFromDB);
			
			String hashedPassword = HashingUtility.getHashValue(password);
			System.out.println(hashedPassword);
			boolean flag=hashedPassword.equals(passwordFromDB);
			System.out.println(flag);
			System.out.println(HashingUtility.getHashValue("abc"));
			System.out.println(HashingUtility.getHashValue("abc"));
			
			if(flag) {
				admin = adminDAO.getAdminByEmailId(emailId);
			}
			else {
				throw new Exception("AdminService.INVALID_CREDENTIALS");
			}
		}else {
			throw new Exception ("AdminService.INVALID_CREDENTIALS");
		}
		return admin;
	}
	
	@Override
	public String registerNewAdmin(Admin admin) throws Exception{
		String registeredWithEmailId=null;
		AdminValidator.validateAdminForRegistration(admin);
			if(adminDAO.checkAvailabilityOfEmailId(admin.getEmailId().toLowerCase())) {
				String emailIdToDB= admin.getEmailId().toLowerCase();
				String passwordToDB = HashingUtility.getHashValue(admin.getPassword());
				
				admin.setEmailId(emailIdToDB);
				admin.setPassword(passwordToDB);
				
				registeredWithEmailId = adminDAO.registerNewAdmin(admin);
			}
			else {
				throw new Exception("AdminService.EMAIL_ID_ALREADY_IN_USE");
			}
			return registeredWithEmailId;
		}
	
	
	
	@Override
	public void updateProfile(Admin admin) throws Exception {
		
		Admin adminFromDB=null;
		 
		AdminValidator.validateAdminForUpdateProfile(admin);
		adminFromDB=adminDAO.getAdminByEmailId(admin.getEmailId());
		
		if(adminFromDB==null){
			throw new Exception("AdminrService.PROFILE_NOT_FOUND");
		}
		else{
				adminDAO.updateProfile(admin);
		}
	}
	
	@Override
	public void changePassword(Admin admin) throws Exception{
		AdminValidator.validatePasswordForAdminChangePassword(admin);
		if(admin.getPassword().equals(admin.getNewPassword())){
			throw new Exception("AdminService.OLD_PASSWORD_NEW_PASSWORD_SAME");
		}
			String hashedPasswordFromDB = adminDAO.getPasswordOfAdmin(admin.getEmailId());
			String hashedCurrentPassword = HashingUtility.getHashValue(admin.getPassword());
			if(!hashedPasswordFromDB.equals(hashedCurrentPassword)) {
				throw new Exception("AdminService.INVALID_CURRENT_PASSWORD");
			}
			String newHashedPassword = HashingUtility.getHashValue(admin.getNewPassword());
			adminDAO.changePassword(admin.getEmailId(), newHashedPassword);
		}
	

}
	