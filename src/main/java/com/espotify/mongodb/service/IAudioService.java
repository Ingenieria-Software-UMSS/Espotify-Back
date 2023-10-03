package com.espotify.mongodb.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.espotify.mongodb.model.Audio;

public interface IAudioService {
	public String addAudio(String title, MultipartFile multipartFile) throws IOException;

	public Audio getAudio(String id) throws IllegalStateException, IOException;
}
