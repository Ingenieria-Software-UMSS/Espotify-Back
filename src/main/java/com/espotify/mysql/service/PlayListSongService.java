package com.espotify.mysql.service;

import java.util.List;

import com.espotify.mysql.model.PlayListSong;

public interface PlayListSongService {
	public PlayListSong getPlayListSongById(Integer playListSongById);

	public PlayListSong addPlayListSong(PlayListSong playListSong);

	public PlayListSong updatePlayListSong(PlayListSong playListSong);

	public PlayListSong deletePlayListSong(Integer playListSongId);

	public List<PlayListSong> getAllPlayListSongs();
}
