package com.music_player.api;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.music_player.model.LikedTrack;
import com.music_player.model.Track;
import com.music_player.service.UserTrackService;

@CrossOrigin
@RestController
@RequestMapping("UserTrackAPI")
public class UserTrackAPI {

	@Autowired
	private UserTrackService userTrackService;
	
	@Autowired
	private Environment environment;
	
	static Logger logger = LogManager.getLogger(UserAPI.class.getName());

	@GetMapping(value = "getAllTracks")
	public ResponseEntity<List<Track>> getAllTracks() throws Exception {
		
		List<Track> tracks = null;
		try
		{
			logger.info("FETCHING ALL THE TRACKS FROM THE USER. ");
			tracks = userTrackService.getAllTracks();
			logger.info("FETCHING TRACK LIST SUCCESSFUL");
			return new ResponseEntity<List<Track>>(tracks, HttpStatus.OK);
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	
	@PostMapping(value = "getLikedTracks")
	public ResponseEntity<List<LikedTrack>> getLikedTracks(@RequestBody String userEmailId) throws Exception {
		
		List<LikedTrack> tracks = null;
		try
		{
			logger.info("FETCHING LIKED TRACK LIST OF THE USER: "+userEmailId);
			tracks = userTrackService.getLikedTracks(userEmailId);
			logger.info("FETCHING LIKED TRACK LIST SUCCESSFUL");
			return new ResponseEntity<List<LikedTrack>>(tracks, HttpStatus.OK);
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	//adding likedTrack
	@PostMapping(value="addLikedTrack")
	public ResponseEntity<?> addLikedTrack(@RequestBody LikedTrack likedTrack){
		try {
			logger.info("ADDING TRACK TO LIKED TRACK LIST, TRACK ID : "+likedTrack.getLikedTrackId()+" BY USER : "+likedTrack.getUserEmailId());
			Integer likedTrackId = userTrackService.addLikedTrack(likedTrack);
			logger.info("TRACK ADDED SUCCESSFULLY TO USER'S LIKED LIST");
			String successMessage = environment.getProperty("UserTrackAPI.LIKE_TRACK_ADDED_SUCCESS ")+likedTrackId;
			likedTrack.setSuccessMessgae(successMessage);
//			likedTrack.setTrackId(likedTrackId);
			return new ResponseEntity<LikedTrack>(likedTrack, HttpStatus.OK);
		}
		catch(Exception e) {
			if(e.getMessage().contains("Validator")) {
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, environment.getProperty(e.getMessage()));
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	
	
	
}
