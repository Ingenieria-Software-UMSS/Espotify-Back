package com.espotify.mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espotify.mysql.model.Song;
import com.espotify.mysql.repository.SongRepository;

@Service
public class SongService implements ISongService {
	@Autowired
	private SongRepository songRepository;

	@Override
	public Song addSong(Song song) {
		return songRepository.save(song);
	}

	@Override
	public Song getSongById(Integer songId) {
		return songRepository.findById(songId).orElse(null);
	}

	@Override
	public List<Song> getSongList() {
		return songRepository.findAll();
	}
}
