package com.espotify.mysql.service;

import java.util.List;

import com.espotify.mysql.model.PlayList;
import com.espotify.mysql.model.User;

public interface PlayListService {

	public PlayList getPlayListById(User user, Integer playListId);

	public PlayList savePlayList(PlayList playList);

	public void deletePlayList(User user, PlayList playList);

	public void deletePlayListById(User user, Integer playListId);

	public List<PlayList> getAllPlayLists(User user);
}
