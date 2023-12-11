package com.espotify.mysql.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espotify.mysql.model.PlayHistorySong;
import com.espotify.mysql.repository.PlayHistorySongRepository;

@Service
public class PlayHistorySongService {
	@Autowired
	private PlayHistorySongRepository playHistorySongRepository;

	public PlayHistorySong savePlayHistorySong(PlayHistorySong playHistorySong) {
		return playHistorySongRepository.save(playHistorySong);
	}

	public PlayHistorySong getPlayHistorySongById(Long playListSongId) {
		return playHistorySongRepository.findById(playListSongId).orElse(null);
	}

	public void deletePlayHistorySong(PlayHistorySong playHistorySong) {
		playHistorySongRepository.delete(playHistorySong);
	}

	public List<PlayHistorySong> getPlayHistorySongList(Long playHistoryId) {
		List<PlayHistorySong> playHistorySongList = playHistorySongRepository.findAll();
		List<PlayHistorySong> filterList = new ArrayList<>();

		for (PlayHistorySong playHistorySong : playHistorySongList)
			if (playHistorySong.getPlayHistory().getPlayHistoryId().equals(playHistoryId))
				filterList.add(playHistorySong);

		return filterList;
	}
}
