package com.espotify.mongodb.model;

import java.io.InputStream;

import lombok.Data;

@Data
public class Audio {
	private String id;
	private String title;
	private InputStream stream;
}
