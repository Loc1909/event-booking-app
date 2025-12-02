package com.eventbooking.dto.event.validation;

import com.eventbooking.dto.event.EventListRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequireCoordinatesForNearbyValidator
    implements ConstraintValidator<RequireCoordinatesForNearby, EventListRequest> {

  @Override
  public boolean isValid(EventListRequest request, ConstraintValidatorContext context) {
    if (request == null || !request.isNearbyType()) {
      return true;
    }

    boolean hasLat = request.getLat() != null;
    boolean hasLng = request.getLng() != null;

    if (hasLat && hasLng) {
      return true;
    }

    context.disableDefaultConstraintViolation();
    if (!hasLat) {
      context
          .buildConstraintViolationWithTemplate("Latitude is required for nearby search")
          .addPropertyNode("lat")
          .addConstraintViolation();
    }
    if (!hasLng) {
      context
          .buildConstraintViolationWithTemplate("Longitude is required for nearby search")
          .addPropertyNode("lng")
          .addConstraintViolation();
    }
    return false;
  }
}


