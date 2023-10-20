package com.espotify.mysql.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Thumbnail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer thumbnailId;
	private String filename;
	private String thumbnailUrl;
	@OneToMany(mappedBy = "thumbnail")
	@JsonIgnore
	private List<Song> songList;
	@OneToMany(mappedBy = "thumbnail")
	@JsonIgnore
	private List<PlayList> playLists;
}
