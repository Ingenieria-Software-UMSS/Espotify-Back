package com.espotify.mysql.service;

import java.util.List;

import com.espotify.mysql.model.PlayList;

public interface PlayListService {

	public PlayList getPlayListById(Integer playListId);

	public PlayList addPlayList(PlayList playList);
	
	public PlayList updatePlayList(PlayList playList);

	public PlayList deletePlayList(Integer playListId);

	public List<PlayList> getAllPlayLists();
}
