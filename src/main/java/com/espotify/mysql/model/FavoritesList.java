package com.espotify.mysql.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritesList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long favoritesListId;
	@OneToOne(mappedBy = "favoritesList", cascade = CascadeType.ALL)
	@JsonIgnore
	private User user;
	@OneToMany(mappedBy = "favoritesList", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<FavoritesListSong> favoritesListSongs;
}
