package com.eventbooking.dto.payment;

import com.eventbooking.common.constant.RegexPattern;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PaymentRequest (
        @NotNull(message = "FIELD_EMPTY")
        @Min(value = 1, message = "INVALID_FIELD_FORMAT")
        Long bookingId,

        @NotBlank(message = "FIELD_EMPTY")
        @Pattern(regexp = RegexPattern.CARD_NUMBER, message = "INVALID_FIELD_FORMAT")
        String cardNumber,

        @NotBlank(message = "FIELD_EMPTY")
        @Pattern(regexp = RegexPattern.CVV, message = "INVALID_FIELD_FORMAT")
        String cvv,

        @NotBlank(message = "FIELD_EMPTY")
        @Pattern(regexp = RegexPattern.CARD_EXPIRER, message = "INVALID_FIELD_FORMAT")
        String expiry

){}
