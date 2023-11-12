package com.espotify.mysql.model;

import java.util.Date;
import java.util.List;

import com.espotify.mysql.dto.SongDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer songId;
	private String songTitle;
	private String songAlbum;
	private String songDuration;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
	@OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PlayListSong> playListSongList;
	@OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<PlayHistorySong> playHistorySongList;

	public SongDto toDto() {
		return new SongDto(
			songId, 
			songTitle, 
			songAlbum, 
			songDuration, 
			uploadDate, 
			songUrl, 
			artist.getArtistName(), 
			thumbnail.getThumbnailUrl()
		);
	}
}
