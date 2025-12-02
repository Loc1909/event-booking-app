package com.eventbooking.mapper;

import com.eventbooking.dto.auth.LoginRequest;
import com.eventbooking.dto.auth.RegisterRequest;
import com.eventbooking.dto.auth.RegisterResponse;
import com.eventbooking.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-02T16:45:59+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23-valhalla (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(RegisterRequest registerRequest) {
        if ( registerRequest == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.fullName( registerRequest.fullName() );
        user.email( registerRequest.email() );

        return user.build();
    }

    @Override
    public RegisterResponse toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        Long userId = null;
        String fullName = null;
        String email = null;

        userId = user.getId();
        fullName = user.getFullName();
        email = user.getEmail();

        RegisterResponse registerResponse = new RegisterResponse( userId, fullName, email );

        return registerResponse;
    }

    @Override
    public User toEntity(LoginRequest loginRequest) {
        if ( loginRequest == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.email( loginRequest.email() );
        user.password( loginRequest.password() );

        return user.build();
    }
}
