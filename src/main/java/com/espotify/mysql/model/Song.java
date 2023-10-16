package com.espotify.mysql.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
// @JsonIgnoreProperties(ignoreUnknown = true)
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer songId;
	private String songTitle;
	private String songAlbum;
	private String songDuration;
	private Date uploadDate;
	private String songUrl;
	@ManyToOne
	@JoinColumn(name = "artist_id")
	@JsonIgnoreProperties("songList")
	private Artist artist;
	@ManyToOne
	@JoinColumn(name = "thumbnail_id")
	@JsonIgnoreProperties("songList")
	private Thumbnail thumbnail;
}
