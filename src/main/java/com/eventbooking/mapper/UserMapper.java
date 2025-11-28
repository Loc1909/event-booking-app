package com.eventbooking.mapper;

import com.eventbooking.dto.auth.AuthResponse;
import com.eventbooking.dto.auth.RegisterRequest;
import com.eventbooking.entity.User;
import com.eventbooking.common.constant.Role;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "password", ignore = true)
  @Mapping(target = "role", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "avatar", ignore = true)
  @Mapping(target = "bookings", ignore = true)
  @Mapping(target = "reminder", ignore = true)
  User toEntity(RegisterRequest registerRequest);

  @Mapping(source = "id", target = "userId")
  AuthResponse toDTO(User user);
}
