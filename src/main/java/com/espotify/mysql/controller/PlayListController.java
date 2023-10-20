package com.espotify.mysql.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.espotify.mysql.model.PlayList;
import com.espotify.mysql.service.PlayListService;

@RestController
public class PlayListController {
	
	@Autowired
	private PlayListService playListService;

	@PostMapping(value = "/play-list")
	@ResponseBody
	public PlayList addPlayList(@RequestBody PlayList playList) {
		playList.setCreationDate(new Date());
		
		return playListService.addPlayList(playList);
	}

	@GetMapping(value = "/play-list/{playListId}")
	@ResponseBody
	public PlayList getPlayList(@PathVariable Integer playListId) {
		return playListService.getPlayListById(playListId);
	}

	@GetMapping(value = "/play-list")
	@ResponseBody
	public List<PlayList> getAllPlayLists() {
		return playListService.getAllPlayLists();
	}
}
