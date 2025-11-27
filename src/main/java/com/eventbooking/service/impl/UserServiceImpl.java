package com.eventbooking.service.impl;

import com.eventbooking.common.constant.ErrorCode;
import com.eventbooking.dto.user.UserProfileUpdateRequest;
import com.eventbooking.dto.user.UserResponse;
import com.eventbooking.exception.EntityNotFoundException;
import com.eventbooking.repository.UserRepository;
import com.eventbooking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserResponse getCurrentUser() {
return null;
  }

  @Override
  public UserResponse updateProfile(UserProfileUpdateRequest request) {
    return null;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email).orElseThrow(() ->
            new EntityNotFoundException(ErrorCode.USER_NOT_FOUND, "User not found"));
  }
}
