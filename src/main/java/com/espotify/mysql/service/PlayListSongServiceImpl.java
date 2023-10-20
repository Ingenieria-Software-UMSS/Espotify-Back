package com.espotify.mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espotify.mysql.model.PlayListSong;
import com.espotify.mysql.repository.PlayListSongRepository;

@Service
public class PlayListSongServiceImpl implements PlayListSongService {

	@Autowired
	private PlayListSongRepository playListSongRepository;

	@Override
	public PlayListSong getPlayListSongById(Integer playListSongId) {
		return playListSongRepository.findById(playListSongId).orElse(null);
	}

	@Override
	public PlayListSong savePlayListSong(PlayListSong playListSong) {
		return playListSongRepository.save(playListSong);
	}

	@Override
	public void deletePlayListSong(PlayListSong playListSong) {
		playListSongRepository.delete(playListSong);
	}

	@Override
	public List<PlayListSong> getAllPlayListSongs() {
		return playListSongRepository.findAll();
	}

	@Override
	public void deletePlayListSongById(Integer playListSongId) {
		playListSongRepository.deleteById(playListSongId);
	}
	
}
