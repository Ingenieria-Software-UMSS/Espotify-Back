package com.espotify.mysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espotify.mysql.model.FavoritesList;
import com.espotify.mysql.repository.FavoritesListRepository;

@Service
public class FavoritesListService {
	@Autowired
	private FavoritesListRepository favoritesListRepository;

	public FavoritesList saveFavoritesList(FavoritesList favoritesList) {
		return favoritesListRepository.save(favoritesList);
	}

	public FavoritesList getFavoritesListById(Long favoritesListId) {
		return favoritesListRepository.findById(favoritesListId).orElse(null);
	}
}
