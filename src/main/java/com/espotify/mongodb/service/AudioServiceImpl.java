package com.espotify.mongodb.service;

import java.io.IOException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.espotify.mongodb.model.Audio;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;

@Service
public class AudioServiceImpl implements AudioService {

	@Autowired
	private GridFsTemplate gridFsTemplate;
	@Autowired
	private GridFsOperations gridFsOperations;

	@Override
	public String addAudio(MultipartFile multipartFile) throws IOException {
		DBObject metaData = new BasicDBObject();

		metaData.put("type", "audio");

		ObjectId id = gridFsTemplate.store(multipartFile.getInputStream(), multipartFile.getName(), multipartFile.getContentType(), metaData);

		return id.toString();
	}

	@Override
	public Audio getAudio(String id) throws IllegalStateException, IOException {
		GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));
		Audio audio = new Audio();

		audio.setStream(gridFsOperations.getResource(file).getInputStream());

		return audio;
	}
	
}
