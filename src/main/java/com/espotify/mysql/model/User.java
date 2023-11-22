package com.espotify.mysql.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userName;
	private String email;
	private String password;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "play_history_id")
	@JsonIgnore
	private PlayHistory playHistory;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "favorites_list_id")
	private FavoritesList favoritesList;
	@OneToMany(mappedBy = "user")
	private List<PlayList> playLists;
	@OneToMany(mappedBy = "user")
	private List<Song> song;

	@Override
	public boolean equals(Object o) {
		if (o instanceof User) {
			User u = (User) o;

			return id.equals(u.id);
		}

		return false;
	}
}
