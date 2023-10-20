package com.espotify.mysql.service;

import java.util.List;

import com.espotify.mysql.model.PlayListSong;

public interface PlayListSongService {
	public PlayListSong getPlayListSongById(Integer playListSongId);

	public PlayListSong savePlayListSong(PlayListSong playListSong);

	public void deletePlayListSong(PlayListSong playListSong);

	public void deletePlayListSongById(Integer playListSongId);

	public List<PlayListSong> getAllPlayListSongs();
}
