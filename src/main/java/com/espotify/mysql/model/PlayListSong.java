package com.espotify.mysql.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class PlayListSong {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer playListSongId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date registrationDate;
	@ManyToOne
	@JoinColumn(name = "song_id")
	@JsonIgnoreProperties("playListSongList")
	private Song song;
	@ManyToOne
	@JoinColumn(name = "play_list_id")
	@JsonIgnoreProperties("playListSongList")
	private PlayList playList;
}
