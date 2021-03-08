package com.music_player.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MP_TRACK_GENRE")
public class TrackGenreEntity {

	@Id
	private String genre;

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
}
