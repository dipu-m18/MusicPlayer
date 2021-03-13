package com.music_player.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MP_TRACK")
public class TrackEntity {
	
		@Id
		@Column(name="TRACK_ID")
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer trackId;
		
		@Column(name="NAME")
		private String name;
		
		@Column(name="GENRE")
		private String genre;
		
		@Column(name="TRACK_URL")
		private String trackUrl;
		
		@Column(name="IMAGE_URL")
		private String imageUrl;
	
		@Column(name="PERFORMED_BY")
		private String performedBy;
		
		@Column(name="WRITTEN_BY")
		private String writtenBy;
		
		@Column(name="PRODUCED_BY")
		private String producedBy;
		
		@Column(name="SOURCE")
		private String source;
		
		@Column(name="ADMIN_EMAIL_ID")
		private String adminEmailId;
		
		
		public String getAdminEmailId() {
			return adminEmailId;
		}

		public void setAdminEmailId(String adminEmailId) {
			this.adminEmailId = adminEmailId;
		}

		public Integer getTrackId() {
			return trackId;
		}

		public void setTrackId(Integer trackId) {
			this.trackId = trackId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getGenre() {
			return genre;
		}

		public void setGenre(String genre) {
			this.genre = genre;
		}

		public String getTrackUrl() {
			return trackUrl;
		}

		public void setTrackUrl(String trackUrl) {
			this.trackUrl = trackUrl;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getPerformedBy() {
			return performedBy;
		}

		public void setPerformedBy(String performedBy) {
			this.performedBy = performedBy;
		}

		public String getWrittenBy() {
			return writtenBy;
		}

		public void setWrittenBy(String writtenBy) {
			this.writtenBy = writtenBy;
		}

		public String getProducedBy() {
			return producedBy;
		}

		public void setProducedBy(String producedBy) {
			this.producedBy = producedBy;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

}
