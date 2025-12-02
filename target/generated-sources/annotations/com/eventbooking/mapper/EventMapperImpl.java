package com.eventbooking.mapper;

import com.eventbooking.dto.event.EventDetailsResponse;
import com.eventbooking.dto.event.EventRequest;
import com.eventbooking.dto.event.EventResponse;
import com.eventbooking.entity.Event;
import com.eventbooking.repository.projection.NearbyEventProjection;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-02T16:45:59+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23-valhalla (Oracle Corporation)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public Event toEntity(EventRequest request) {
        if ( request == null ) {
            return null;
        }

        Event.EventBuilder<?, ?> event = Event.builder();

        event.dateTime( stringToLocalDate( request.dateTime() ) );
        event.title( request.title() );
        event.location( request.location() );
        event.price( request.price() );
        event.description( request.description() );
        event.imageUrl( request.imageUrl() );

        return event.build();
    }

    @Override
    public void toEntity(Event event, EventRequest request) {
        if ( request == null ) {
            return;
        }

        event.setDateTime( stringToLocalDate( request.dateTime() ) );
        event.setTitle( request.title() );
        event.setLocation( request.location() );
        event.setPrice( request.price() );
        event.setDescription( request.description() );
        event.setImageUrl( request.imageUrl() );
    }

    @Override
    public EventDetailsResponse toDetailsResponse(Event event) {
        if ( event == null ) {
            return null;
        }

        EventDetailsResponse.EventDetailsResponseBuilder eventDetailsResponse = EventDetailsResponse.builder();

        eventDetailsResponse.id( event.getId() );
        eventDetailsResponse.title( event.getTitle() );
        eventDetailsResponse.dateTime( event.getDateTime() );
        eventDetailsResponse.location( event.getLocation() );
        eventDetailsResponse.price( event.getPrice() );
        eventDetailsResponse.description( event.getDescription() );
        eventDetailsResponse.imageUrl( event.getImageUrl() );

        return eventDetailsResponse.build();
    }

    @Override
    public EventResponse toResponse(Event event) {
        if ( event == null ) {
            return null;
        }

        EventResponse.EventResponseBuilder eventResponse = EventResponse.builder();

        eventResponse.id( event.getId() );
        eventResponse.title( event.getTitle() );
        eventResponse.dateTime( event.getDateTime() );
        eventResponse.location( event.getLocation() );
        eventResponse.price( event.getPrice() );
        eventResponse.description( event.getDescription() );
        eventResponse.imageUrl( event.getImageUrl() );

        return eventResponse.build();
    }

    @Override
    public EventResponse toResponse(NearbyEventProjection projection) {
        if ( projection == null ) {
            return null;
        }

        EventResponse.EventResponseBuilder eventResponse = EventResponse.builder();

        eventResponse.id( projection.getId() );
        eventResponse.title( projection.getTitle() );
        eventResponse.dateTime( projection.getDateTime() );
        eventResponse.location( projection.getLocation() );
        eventResponse.price( projection.getPrice() );
        eventResponse.description( projection.getDescription() );
        eventResponse.imageUrl( projection.getImageUrl() );

        return eventResponse.build();
    }
}
