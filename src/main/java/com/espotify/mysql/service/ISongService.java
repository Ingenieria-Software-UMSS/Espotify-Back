package com.espotify.mysql.service;

import java.util.List;

import com.espotify.mysql.model.Song;

public interface ISongService {
	public Song addSong(Song song);

	public Song getSongById(Integer songId);

	public List<Song> getSongList();
}
