package com.eventbooking.dto.event;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventDetailsResponse {
    Long id;
    String title;
    LocalDateTime dateTime;
    String location;
    BigDecimal price;
    String description;
    String imageUrl;
}
