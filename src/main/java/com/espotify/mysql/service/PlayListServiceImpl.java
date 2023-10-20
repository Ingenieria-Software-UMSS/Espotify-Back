package com.espotify.mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espotify.mysql.model.PlayList;
import com.espotify.mysql.repository.PlayListRepository;

@Service
public class PlayListServiceImpl implements PlayListService {

	@Autowired
	private PlayListRepository playListRepository;

	@Override
	public PlayList getPlayListById(Integer playListId) {
		return playListRepository.findById(playListId).orElse(null);
	}

	@Override
	public PlayList savePlayList(PlayList playList) {
		return playListRepository.save(playList);
	}

	@Override
	public void deletePlayList(PlayList playList) {
		playListRepository.delete(playList);
	}

	@Override
	public List<PlayList> getAllPlayLists() {
		return playListRepository.findAll();
	}

	@Override
	public void deletePlayListById(Integer playListId) {
		playListRepository.deleteById(playListId);
	}
	
}
