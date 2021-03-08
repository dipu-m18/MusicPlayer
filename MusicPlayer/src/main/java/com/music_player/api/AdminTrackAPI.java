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

import com.music_player.model.Track;
import com.music_player.service.AdminTrackService;
//http://localhost:3333/MUSICPLAYER_Server/AdminTrackAPI/addTrackToAdminTrackList
@CrossOrigin
@RestController
@RequestMapping("AdminTrackAPI")
public class AdminTrackAPI {

	@Autowired
	private AdminTrackService adminTrackService;
	
	@Autowired 
	private Environment environment;
	
	static Logger logger = LogManager.getLogger(AdminTrackAPI.class.getName());
	
//	Adding songs to track list
	@PostMapping(value="addTrackToAdminTrackList")
	public ResponseEntity<?> addTrackToAdminTrackList(@RequestBody Track track)throws Exception{
		try {
			
			logger.info("ADDING TRACK TO ADMIN TRACK LIST, TRACK NAME: "+track.getName()+"\tADMIN NAME: "+track.getAdminEmailId());
			Integer newTrackId = adminTrackService.addNewTrack(track);
			logger.info("TRACK ADDED SUCCESSFULLY TO ADMIN CATALOG, TRACK NAME: "+track.getName()+"\t ADMIN NAME = "+track.getAdminEmailId());
			String successMessage = environment.getProperty("AdminTrackAPI.TRACK_ADDED_SUCCESSFULLY")+newTrackId;
			track.setSuccessMessage(successMessage);
			track.setTrackId(newTrackId);
			return new ResponseEntity<Track>(track, HttpStatus.OK);
		}
		catch(Exception e) {
			if(e.getMessage().contains("Validator")) {
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, environment.getProperty(e.getMessage()));
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	
//	Fetching track list
	@PostMapping(value="getTrackList")
	public ResponseEntity<?> getTrackList(@RequestBody String adminEmailId) throws Exception{
		try {
			logger.info("FETCHING TRACK LIST OF ADMIN: "+adminEmailId);
			List<Track> trackList = adminTrackService.getTrackList(adminEmailId);
			logger.info("FETCHING TRACK LIST SUCCESSFUL");
			return new ResponseEntity<List<Track>>(trackList, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
//	Deleting track from track list
	@PostMapping(value="removeTrack")
	public ResponseEntity<?> removeProduct(@RequestBody Track track) throws Exception {
		try {
			logger.info("REMOVING TRACK DETAILS, TRACK NAME: "+track.getName()+" with id: "+track.getTrackId());
			Integer deletedTrack = adminTrackService.removeTrack(track);
			logger.info("TRACK DETAILS REMOVED SUCCESSFULLY, TRACK ID: "+track.getTrackId());
			String successMessage = environment.getProperty("AdminTrackAPI.TRACK_DELETED_SUCCESSFULLY")+deletedTrack;
			track.setTrackId(deletedTrack);
			track.setSuccessMessage(successMessage);
			return new ResponseEntity<Track>(track, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
}
