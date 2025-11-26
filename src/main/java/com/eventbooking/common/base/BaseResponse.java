package com.eventbooking.common.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
  boolean success;
  String message;
  T data;

  Object errors;

  @Builder.Default
  Instant timestamp = Instant.now();
}
