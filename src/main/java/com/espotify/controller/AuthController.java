package com.espotify.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.espotify.config.UserAuthenticationProvider;
import com.espotify.dto.CredentialsDto;
import com.espotify.dto.SignUpDto;
import com.espotify.dto.UserDto;
import com.espotify.mysql.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {
	
	private final UserService userService;
	private final UserAuthenticationProvider userAuthenticationProvider;

	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
		UserDto user = userService.login(credentialsDto);

		user.setToken(userAuthenticationProvider.createToken(user.getEmail()));

		return ResponseEntity.ok(user);
	}

	@PostMapping("/register")
	public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
		UserDto user = userService.register(signUpDto);

		user.setToken(userAuthenticationProvider.createToken(user.getEmail()));

		return ResponseEntity.created(URI.create("/users/" + user.getId()))
				.body(user);
	}
}
