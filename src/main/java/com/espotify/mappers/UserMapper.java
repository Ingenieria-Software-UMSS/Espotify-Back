package com.espotify.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.espotify.dto.SignUpDto;
import com.espotify.dto.UserDto;
import com.espotify.mysql.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

	public UserDto toUserDto(User user);

	@Mapping(target = "password", ignore = true)
	public User signUpToUser(SignUpDto userDto);
}
