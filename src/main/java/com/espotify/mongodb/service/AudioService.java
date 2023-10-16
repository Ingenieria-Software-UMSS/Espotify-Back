package com.espotify.mongodb.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.espotify.mongodb.model.Audio;

public interface AudioService {
	public String addAudio(MultipartFile multipartFile) throws IOException;

	public Audio getAudio(String id) throws IllegalStateException, IOException;
}
