package com.espotify.mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.espotify.mysql.model.Thumbnail;
import com.espotify.mysql.repository.ThumbnailRepository;

@Service
public class ThumbnailServiceImpl implements ThumbnailService {
	@Autowired
	private ThumbnailRepository thumbnailRepository;

	@Override
	public Thumbnail getThumbnailById(Integer thumbnailId) {
		return thumbnailRepository.findById(thumbnailId).orElse(null);
	}

	@Override
	public Thumbnail saveThumbnail(Thumbnail thumbnail) {
		return thumbnailRepository.save(thumbnail);
	}

	@Override
	public List<Thumbnail> getAllThumbnails() {
		return thumbnailRepository.findAll();
	}

	@Override
	public void deleteThumbnail(Thumbnail thumbnail) {
		thumbnailRepository.delete(thumbnail);
	}
}
