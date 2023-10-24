package com.espotify.mappers;

import com.espotify.dto.SignUpDto;
import com.espotify.dto.UserDto;
import com.espotify.mysql.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-23T23:49:47-0400",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Debian)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.userName( user.getUserName() );
        userDto.email( user.getEmail() );

        return userDto.build();
    }

    @Override
    public User signUpToUser(SignUpDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.userName( userDto.getUserName() );
        user.email( userDto.getEmail() );

        return user.build();
    }
}
