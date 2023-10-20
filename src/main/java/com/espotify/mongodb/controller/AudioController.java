package com.espotify.mongodb.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.espotify.mongodb.model.Audio;
import com.espotify.mongodb.service.AudioService;

@RestController
@RequestMapping("/storage")
public class AudioController {
	@Autowired
	private AudioService audioService;

	@PostMapping(value = "/audio")
	@ResponseBody
	public String addAudio(@RequestParam("file") MultipartFile multipartFile) 
			throws IOException {
		String id = audioService.addAudio(multipartFile);

		return "/storage/audio/" + id;
	}

	@GetMapping(value = "/audio/{id}")
	public @ResponseBody byte[] getAudio(@PathVariable String id)
			throws IllegalStateException, IOException {
		Audio audio = audioService.getAudio(id);

		return audio.getStream().readAllBytes();
	}
}
