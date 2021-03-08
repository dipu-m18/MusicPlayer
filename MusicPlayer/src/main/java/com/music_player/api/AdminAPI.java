package com.music_player.api;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.music_player.model.Admin;
import com.music_player.service.AdminService;
import com.music_player.service.AdminServiceImp;

@CrossOrigin
@RestController
@RequestMapping("AdminAPI")
public class AdminAPI {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private Environment environment;
	
//	try
//	{
//		logger.info("SELLER TRYING TO REGISTER. SELLER EMAIL ID: "+seller.getEmailId());
//		
//		String registeredWithEmailID = sellerService.registerNewSeller(seller);
//		
//		logger.info("SELLER REGISTRATION SUCCESSFUL. SELLER EMAIL ID: "+seller.getEmailId());
//		
//		registeredWithEmailID = environment.getProperty("SellerAPI.SELLER_REGISTRATION_SUCCESS")+registeredWithEmailID;
//		
//		return new ResponseEntity<String>(registeredWithEmailID, HttpStatus.OK);
//		
//	}
//	catch (Exception e){
//		if(e.getMessage().contains("Validator")){
//			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, environment.getProperty(e.getMessage()));
//		}
//		throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()));
//	}

	static Logger logger = LogManager.getLogger(AdminAPI.class.getName());
	@PostMapping(value="registerAdmin")
	public ResponseEntity<String> registerAdmin(@RequestBody Admin admin) throws Exception{
		try {
			logger.info("ADMIN TYING TO REGISTER. ADMIN EMAIL ID: "+admin.getEmailId());
			String registeredWithEmailID = adminService.registerNewAdmin(admin);
			logger.info("ADMIN REGISTRATION SUCCESSFUL. ADMIN EMAIL ID: "+admin.getEmailId());
			registeredWithEmailID = environment.getProperty("AdminAPI.ADMIN_REGISTRATION_SUCCESS")+registeredWithEmailID;
			return new ResponseEntity<String>(registeredWithEmailID, HttpStatus.OK);
		}
		catch (Exception e){
			if(e.getMessage().contains("Validator")){
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, environment.getProperty(e.getMessage()));
			}
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()));
		}
		
	}
	
	
	@PostMapping(value = "adminLogin")
	public ResponseEntity<Admin> authenticateAdmin(@RequestBody Admin admin) throws Exception{
		
		try {
			logger.info("ADMIN TRING TO LOGIN. ADMIN EMAIL ID: "+admin.getEmailId());
			Admin adminFromDB = adminService.authenticateAdmin(admin.getEmailId(), admin.getPassword());
			logger.info("ADMIN LOGIN SUCCESSFUL. ADMIN EMAIL ID: "+admin.getEmailId());
			return new ResponseEntity<Admin>(adminFromDB, HttpStatus.OK);
			}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "updateProfile")
	public ResponseEntity<String> updateAdminProfile(@RequestBody Admin admin) throws Exception {
		try {
			logger.info("ADMIN TRYING TO UPDATE PROFILE. ADMIN EMAIL ID "+admin.getEmailId());
			String modificationSuccessMsg = environment.getProperty("AdminAPI.ADMIN_DETAILS_UPDATION_SUCCESS");
			return new ResponseEntity<String>(modificationSuccessMsg, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value="changePassword")
	public ResponseEntity<String> changePassword(@RequestBody Admin admin) throws Exception {
		try {
			logger.info("ADMIN TRYING TO CHANGE PASSWORD. ADMIN AMAIL ID: "+admin.getEmailId());
			adminService.changePassword(admin);
			logger.info("ADMIN CHANGE PASSWORD SUCCESSFUL. ADMIN EMAIL ID: "+admin.getEmailId());
			String modificationSuccessMsg = environment.getProperty("AdminAPI.ADMIN_PASSWORD_CHANGE_SUCCESS");
			return new ResponseEntity<String>(modificationSuccessMsg, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, environment.getProperty(e.getMessage()));
		}
	}
	
	
}
