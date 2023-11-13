package com.espotify.mysql.dto;

import com.espotify.mysql.model.FavoritesList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoritesListSongDto {
	private Long favoritesListSongId;
	private FavoritesList favoritesList;
	private SongDto song;
}
