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

import com.music_player.model.User;
import com.music_player.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("UserAPI")
public class UserAPI {

	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment environment;
	
	static Logger logger = LogManager.getLogger(UserAPI.class.getName());
	
	@PostMapping(value = "userLogin")
	public ResponseEntity<User> authenticateUser(@RequestBody User user) throws Exception{
		try {
			logger.info("USER TRYING TO LOGIN, VALIDATING CREDENTIALS. USER EMAIL ID: "+user.getEmailId());
			User userFromDB = userService.authenticateUser(user.getEmailId(), user.getPassword());
			logger.info("CUSTOMER LOGIN SUCCESS, CUSTOMER EMAIL : "+userFromDB.getEmailId());
			
			return new ResponseEntity<User>(userFromDB, HttpStatus.OK);
		}catch (Exception e) {
			System.out.println("Exceptionn");
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "registerUser")
	public ResponseEntity<String> registerCustomer(@RequestBody User user) throws Exception {
		try
		{
			logger.info("USER TRYING TO REGISTER. USER EMAIL ID: "+user.getEmailId());
			
			String registeredWithEmailID = userService.registerNewUser(user);
			registeredWithEmailID = environment.getProperty("UserAPI.CUSTOMER_REGISTRATION_SUCCESS")+registeredWithEmailID;
			return new ResponseEntity<String>(registeredWithEmailID, HttpStatus.OK);
			
		}
		catch (Exception e){
			if(e.getMessage().contains("Validator")){
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, environment.getProperty(e.getMessage()));
			}
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value = "updateProfile")
	public ResponseEntity<String> updateUserProfile(@RequestBody User user) throws Exception {

		try
		{
			userService.updateProfile(user);
			String modificationSuccessMsg = environment.getProperty("UserAPI.USER_DETAILS_UPDATION_SUCCESS");
			return new ResponseEntity<String>(modificationSuccessMsg, HttpStatus.OK);	
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, environment.getProperty(e.getMessage()));
		}
	}
	
	
	@PostMapping(value = "changePassword")
	public ResponseEntity<String> changePassword(@RequestBody User user) throws Exception {

		try
		{
			userService.changePassword(user.getEmailId(), user.getPassword(), user.getNewPassword());
			String modificationSuccessMsg = environment.getProperty("UserAPI.USER_PASSWORD_CHANGE_SUCCESS");
			return new ResponseEntity<String>(modificationSuccessMsg, HttpStatus.OK);	
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, environment.getProperty(e.getMessage()));
		}
	}
	
	
	
	
}
