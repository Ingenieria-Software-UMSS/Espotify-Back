package com.espotify.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espotify.mysql.model.FavoritesList;

public interface FavoritesListRepository extends JpaRepository<FavoritesList, Long> {
	
}
