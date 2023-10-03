package com.espotify.mysql.model;

import java.util.List;

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
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer artistId;
	private String artistName;
	@OneToMany(mappedBy = "artist")
	private List<Song> songList;
}
