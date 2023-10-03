package com.espotify.mongodb.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.espotify.mongodb.model.Audio;
import com.espotify.mongodb.service.IAudioService;

@RestController
@RequestMapping("/storage")
public class AudioController {
	@Autowired
	private IAudioService audioService;

	@PostMapping(value = "/audio/add")
	public String addAudio(@RequestParam("title") String title, @RequestParam("file") MultipartFile multipartFile, Model model) throws IOException {
		String id = audioService.addAudio(title, multipartFile);

		return "/storage/audio/" + id;
	}

	@GetMapping(value = "/audio/{id}")
	public @ResponseBody byte[] getAudio(@PathVariable String id, Model model) throws IllegalStateException, IOException {
		Audio audio = audioService.getAudio(id);

		return audio.getStream().readAllBytes();
	}
}
