package com.espotify.mysql.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.espotify.mysql.model.PlayList;
import com.espotify.mysql.model.User;
import com.espotify.mysql.repository.PlayListRepository;

@Service
public class PlayListServiceImpl implements PlayListService {

	@Autowired
	private PlayListRepository playListRepository;

	@Override
	public PlayList getPlayListById(User user, Integer playListId) {
		PlayList playList = playListRepository.findById(playListId).orElse(null);

		if (playList != null && playList.getUser().equals(user))
			return playList;

		return null;
	}

	@Override
	public PlayList savePlayList(PlayList playList) {
		return playListRepository.save(playList);
	}

	@Override
	public void deletePlayList(User user, PlayList playList) {
		if (playList.getUser().equals(user))
			playListRepository.delete(playList);
	}

	@Override
	public List<PlayList> getAllPlayLists(User user) {
		List<PlayList> result = new ArrayList<>();
		List<PlayList> playLists = playListRepository.findAll();

		playLists.forEach(playList -> { 
			if (playList.getUser().equals(user)) {
				result.add(playList);
			}
		});

		return result;
	}

	@Override
	public void deletePlayListById(User user, Integer playListId) {
		PlayList playList = getPlayListById(user, playListId);

		if (playList != null)
			playListRepository.deleteById(playListId);
	}
	
}
