package com.eventbooking.controller;

import com.eventbooking.common.base.BaseResponse;
import com.eventbooking.dto.auth.AuthResponse;
import com.eventbooking.dto.auth.RegisterRequest;
import com.eventbooking.service.AuthService;
import com.eventbooking.util.ApiResponseBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<BaseResponse<AuthResponse>> register(
      @Valid @RequestBody RegisterRequest registerRequest) {
    AuthResponse data = authService.register(registerRequest);
    return ResponseEntity.ok(ApiResponseBuilder.success("Registration successful", data));
  }
}
