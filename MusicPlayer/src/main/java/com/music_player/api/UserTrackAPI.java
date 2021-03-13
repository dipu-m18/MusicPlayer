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
			tracks = userTrackService.getAllTracks();
			
			return new ResponseEntity<List<Track>>(tracks, HttpStatus.OK);
		}
		catch (Exception e) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	
	
	
}
