package com.espotify.mysql.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class PlayList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer playListId;
	private String playListName;
	private String playListDescription;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate;
	@ManyToOne
	@JsonIgnoreProperties("thumbnail")
	private Thumbnail thumbnail;
	@OneToMany(mappedBy = "playList", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("play_list")
	private List<PlayListSong> playListSongList;
	@ManyToOne
	@JsonIgnoreProperties("user")
	@JsonIgnore
	private User user;
}
