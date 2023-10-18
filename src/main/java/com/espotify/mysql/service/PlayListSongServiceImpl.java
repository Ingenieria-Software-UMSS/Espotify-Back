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
	public PlayListSong getPlayListSongById(Integer playListSongById) {
		return playListSongRepository.findById(playListSongById).orElse(null);
	}

	@Override
	public PlayListSong addPlayListSong(PlayListSong playListSong) {
		return playListSongRepository.save(playListSong);
	}

	@Override
	public PlayListSong updatePlayListSong(PlayListSong playListSong) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'updatePlayListSong'");
	}

	@Override
	public PlayListSong deletePlayListSong(Integer playListSongId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'deletePlayListSong'");
	}

	@Override
	public List<PlayListSong> getAllPlayListSongs() {
		return playListSongRepository.findAll();
	}
	
}
