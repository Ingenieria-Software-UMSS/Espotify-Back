package com.espotify.mysql.service;

import java.util.List;

import com.espotify.mysql.model.Thumbnail;

public interface ThumbnailService {
	public Thumbnail getThumbnailById(Integer thumbnailId);

	public List<Thumbnail> getAllThumbnails();

	public Thumbnail saveThumbnail(Thumbnail thumbnail);

	public void deleteThumbnail(Thumbnail thumbnail);
}
