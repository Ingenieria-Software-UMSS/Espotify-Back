package com.espotify.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espotify.mysql.model.PlayListSong;

public interface PlayListSongRepository extends JpaRepository<PlayListSong, Integer> {
	
}
