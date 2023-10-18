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
	public PlayList addPlayList(PlayList playList) {
		return playListRepository.save(playList);
	}

	@Override
	public PlayList updatePlayList(PlayList playList) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'updatePlayList'");
	}

	@Override
	public PlayList deletePlayList(Integer playListId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'deletePlayList'");
	}

	@Override
	public List<PlayList> getAllPlayLists() {
		return playListRepository.findAll();
	}
	
}
