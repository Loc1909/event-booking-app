package com.eventbooking.dto.booking;

import com.eventbooking.dto.event.EventResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    Long bookingId;
    EventResponse event;
    Integer quantity;
    BigDecimal totalPrice;
}