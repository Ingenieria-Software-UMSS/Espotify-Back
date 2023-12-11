package com.espotify.mysql.model;

import com.espotify.mysql.dto.FavoritesListSongDto;
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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoritesListSong {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long favoritesListSongId;
	@ManyToOne
	@JoinColumn(name = "favorites_list_id")
	@JsonIgnoreProperties("favoritesListSongs")
	private FavoritesList favoritesList;
	@ManyToOne
	@JoinColumn(name = "favoritesListSongs")
	@JsonIgnoreProperties("favoritesListSongs")
	private Song song;

	public FavoritesListSongDto toDto() {
		return new FavoritesListSongDto(favoritesListSongId, favoritesList, song.toDto());
	}
}
