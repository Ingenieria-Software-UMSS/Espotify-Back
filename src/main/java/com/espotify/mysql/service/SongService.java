package com.espotify.mysql.service;

import java.util.List;

import com.espotify.mysql.model.Song;

public interface SongService {
	public Song addSong(Song song);

	public Song getSongById(Integer songId);

	public List<Song> getAllSongs();

	public Song updateSong(Song song);

	public Song deleteSong(Integer songId);
}
