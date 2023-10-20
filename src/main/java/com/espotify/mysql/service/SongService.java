package com.espotify.mysql.service;

import java.util.List;

import com.espotify.mysql.model.Song;

public interface SongService {
	public Song saveSong(Song song);

	public Song getSongById(Integer songId);

	public List<Song> getAllSongs();

	public void deleteSong(Song song);
}
