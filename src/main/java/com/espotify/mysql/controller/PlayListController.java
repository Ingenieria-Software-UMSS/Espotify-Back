package com.espotify.mysql.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		
		return playListService.savePlayList(playList);
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

	@PutMapping(value = "play-list/{playListId}")
	@ResponseBody
	public PlayList updatePlayList(@PathVariable Integer playListId, @RequestBody PlayList playList) {
		PlayList updatedPlayList = playListService.getPlayListById(playListId);

		updatedPlayList.setPlayListName(playList.getPlayListName());
		updatedPlayList.setPlayListDescription(playList.getPlayListDescription());

		if (playList.getThumbnail() != null)
			updatedPlayList.setThumbnail(playList.getThumbnail());

		return playListService.savePlayList(updatedPlayList);
	}

	@DeleteMapping(value = "play-list/{playListId}")
	public ResponseEntity<Map<String, Boolean>> deletePlayList(@PathVariable Integer playListId) {
		PlayList playList = playListService.getPlayListById(playListId);
		
		playListService.deletePlayList(playList);

		Map<String, Boolean> response = new HashMap<>();

		response.put("deleted", Boolean.TRUE);

		return ResponseEntity.ok(response);
	}
}
