package com.espotify.mysql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espotify.mysql.model.PlayHistory;
import com.espotify.mysql.repository.PlayHistoryRepository;

@Service
public class PlayHistoryService {
	@Autowired
	private PlayHistoryRepository playHistoryRepository;

	public PlayHistory savePlayHistory(PlayHistory playHistory) {
		return playHistoryRepository.save(playHistory);
	}

	public PlayHistory getPlayHistoryById(Long playHistoryId) {
		return playHistoryRepository.findById(playHistoryId).orElse(null);
	}
}
