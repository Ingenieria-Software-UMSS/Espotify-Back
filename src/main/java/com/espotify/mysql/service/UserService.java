package com.espotify.mysql.service;

import java.nio.CharBuffer;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.espotify.dto.CredentialsDto;
import com.espotify.dto.SignUpDto;
import com.espotify.dto.UserDto;
import com.espotify.exceptions.AppException;
import com.espotify.mappers.UserMapper;
import com.espotify.mysql.model.User;
import com.espotify.mysql.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	public UserDto findByLogin(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new AppException("Unknow user", HttpStatus.NOT_FOUND));
		
		return userMapper.toUserDto(user);
	}

	public UserDto login(CredentialsDto credentialsDto) {
		User user = userRepository.findByEmail(credentialsDto.getEmail())
				.orElseThrow(() -> new AppException("Unknow user", HttpStatus.NOT_FOUND));
		
		if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
			return userMapper.toUserDto(user);
		}
		
		throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
	}

	public UserDto register(SignUpDto userDto) {
		Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

		if (optionalUser.isPresent())
			throw new AppException("This user already exists", HttpStatus.BAD_REQUEST);
		
		User user = userMapper.signUpToUser(userDto);

		user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));

		User savedUser = userRepository.save(user);

		return userMapper.toUserDto(savedUser);
	}
}
