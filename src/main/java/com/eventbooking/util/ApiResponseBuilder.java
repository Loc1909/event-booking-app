package com.eventbooking.util;

import com.eventbooking.common.base.BaseResponse;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class ApiResponseBuilder {

  public static <T> BaseResponse<T> success(String message, T data) {
    return BaseResponse.<T>builder()
            .success(true)
            .message(message)
            .data(data)

            // => Để Buider.Default nên đoạn này ko cần thiết
            //.timestamp(Instant.now())
            .build();
  }

  // Dùng ở GlobarExceptionHandler
  public static BaseResponse<?> error (String message, Object errors) {
    return BaseResponse.builder()
            .success(false)
            .message(message)
            .errors(errors)
            .build();
  }
}
