package com.espotify.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espotify.mysql.model.FavoritesListSong;

public interface FavoritesListSongRepository extends JpaRepository<FavoritesListSong, Long> {
	
}
