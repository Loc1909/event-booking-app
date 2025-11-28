package com.eventbooking.service.impl;

import com.eventbooking.common.constant.Role;
import com.eventbooking.dto.auth.AuthResponse;
import com.eventbooking.dto.auth.LoginRequest;
import com.eventbooking.dto.auth.RegisterRequest;
import com.eventbooking.mapper.UserMapper;
import com.eventbooking.repository.UserRepository;
import com.eventbooking.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  private final UserMapper userMapper;

  @Override
  @Transactional
  public AuthResponse login(LoginRequest request) {
    return null;
  }

  @Override
  public AuthResponse register(RegisterRequest registerRequest) {
    var registerUser = userMapper.toEntity(registerRequest);
    registerUser.setRole(Role.USER);
    registerUser.setPassword(passwordEncoder.encode(registerRequest.password()));

    return userMapper.toDTO(userRepository.save(registerUser));
  }
}
