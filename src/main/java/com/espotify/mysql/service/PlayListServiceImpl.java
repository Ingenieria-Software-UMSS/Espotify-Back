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

		if (playList == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Play List no encontrada");
		
		if(!playList.getUser().getId().equals(user.getId()))
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Artista no encontrado");

		return playList;
	}

	@Override
	public PlayList savePlayList(PlayList playList) {
		return playListRepository.save(playList);
	}

	@Override
	public void deletePlayList(User user, PlayList playList) {
		if (playList.getUser().getId().equals(user.getId()))
			playListRepository.delete(playList);
	}

	@Override
	public List<PlayList> getAllPlayLists(User user) {
		List<PlayList> result = new ArrayList<>();
		List<PlayList> playLists = playListRepository.findAll();

		playLists.forEach(playList -> { 
			if (playList.getUser().getId().equals(user.getId())) {
				result.add(playList);
			}
		});

		return result;
	}

	@Override
	public void deletePlayListById(User user, Integer playListId) {
		playListRepository.deleteById(playListId);
	}
	
}
