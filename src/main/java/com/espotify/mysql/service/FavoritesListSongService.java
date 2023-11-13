package com.espotify.mysql.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espotify.mysql.model.FavoritesListSong;
import com.espotify.mysql.repository.FavoritesListSongRepository;

@Service
public class FavoritesListSongService {
	@Autowired
	private FavoritesListSongRepository favoritesListSongRepository;

	public FavoritesListSong saveFavoritesListSong(FavoritesListSong favoritesListSong) {
		return favoritesListSongRepository.save(favoritesListSong);
	}

	public void deleteFavoritesListSong(FavoritesListSong favoritesListSong) {
		favoritesListSongRepository.delete(favoritesListSong);
	}

	public FavoritesListSong getFavoritesListSongById(Long favoritesListSongId) {
		return favoritesListSongRepository.findById(favoritesListSongId).orElse(null);
	}

	public List<FavoritesListSong> getAllFavoritesListSong(Long favoritesListId) {
		List<FavoritesListSong> favoritesListSongs = favoritesListSongRepository.findAll();
		List<FavoritesListSong> responseList = new ArrayList<>();

		for (FavoritesListSong favoritesListSong : favoritesListSongs)
			if (favoritesListSong.getFavoritesList().getFavoritesListId().equals(favoritesListId))
				responseList.add(favoritesListSong);

		return responseList;
	}
}
