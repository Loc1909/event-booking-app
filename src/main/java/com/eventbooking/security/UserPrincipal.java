package com.eventbooking.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserPrincipal {
  private final Long id;
  private final String email;
}

