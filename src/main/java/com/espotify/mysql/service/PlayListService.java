package com.espotify.mysql.service;

import java.util.List;

import com.espotify.mysql.model.PlayList;

public interface PlayListService {

	public PlayList getPlayListById(Integer playListId);

	public PlayList savePlayList(PlayList playList);

	public void deletePlayList(PlayList playList);

	public void deletePlayListById(Integer playListId);

	public List<PlayList> getAllPlayLists();
}
