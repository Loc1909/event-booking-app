package com.eventbooking.util;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FutureDateStringValidator implements ConstraintValidator<FutureDateString, String> {

    private static final String ISO_DATE_PATTERN = "yyyy-MM-dd";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) return true; // @NotBlank check riêng

        String combinedMessage = "";

        LocalDateTime dateTime;

        // 1️⃣ check pattern
        try {
            dateTime = LocalDate.parse(value, DateTimeFormatter.ofPattern(ISO_DATE_PATTERN))
                    .atStartOfDay();
        } catch (DateTimeParseException e) {
            combinedMessage += "Required a ISO pattern";
            dateTime = null;
        }

        // 2️⃣ check future nếu pattern hợp lệ
        if (dateTime != null && !dateTime.isAfter(LocalDateTime.now())) {
            combinedMessage += "Date & Time must be in the future";
        }

        if (!combinedMessage.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(combinedMessage)
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
