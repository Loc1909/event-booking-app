package com.eventbooking.mapper;
import com.eventbooking.dto.event.EventRequest;
import com.eventbooking.dto.event.EventDetailsResponse;
import com.eventbooking.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface EventMapper {
    @Mapping(target = "longitude", ignore = true)
    @Mapping(target = "latitude", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "dateTime", source = "dateTime", qualifiedByName = "stringToLocalDate")
    Event toEntity(EventRequest request);

    @Mapping(target = "longitude", ignore = true)
    @Mapping(target = "latitude", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "dateTime", source = "dateTime", qualifiedByName = "stringToLocalDate")
    void toEntity(EventRequest request, @MappingTarget Event event);

    EventDetailsResponse toResponse(Event event);

    @Named("stringToLocalDate")
    default LocalDate stringToLocalDate(String dateTime) {
        if (dateTime == null || dateTime.isBlank()) return null;
        return LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
