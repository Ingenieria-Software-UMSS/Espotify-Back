package com.espotify.mysql.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PlayHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long playHistoryId;
	@OneToOne(mappedBy = "playHistory")
	@JsonIgnore
	private User user;
	@OneToMany(mappedBy = "playHistory", cascade = CascadeType.ALL)
	// @JsonIgnoreProperties("playHistory")
	@JsonIgnore
	private List<PlayHistorySong> playHistorySong;
}
