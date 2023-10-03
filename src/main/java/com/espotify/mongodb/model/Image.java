package com.espotify.mongodb.model;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection = "images")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Image {
	@Id
	private String id;
	private String title;
	private Binary image;
}
