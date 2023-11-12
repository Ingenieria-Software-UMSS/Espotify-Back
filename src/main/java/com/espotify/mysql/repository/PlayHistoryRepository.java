package com.espotify.mysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espotify.mysql.model.PlayHistory;

public interface PlayHistoryRepository extends JpaRepository<PlayHistory, Long> {
	
}
