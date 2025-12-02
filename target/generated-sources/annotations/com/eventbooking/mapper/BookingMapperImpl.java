package com.eventbooking.mapper;

import com.eventbooking.dto.booking.BookingCreateRequest;
import com.eventbooking.dto.booking.BookingResponse;
import com.eventbooking.dto.event.EventResponse;
import com.eventbooking.entity.Booking;
import com.eventbooking.entity.Event;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-02T16:45:59+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23-valhalla (Oracle Corporation)"
)
@Component
public class BookingMapperImpl implements BookingMapper {

    @Override
    public void toEntity(Booking booking, BookingCreateRequest request) {
        if ( request == null ) {
            return;
        }

        booking.setQuantity( request.quantity() );
    }

    @Override
    public BookingResponse toBookingResponse(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        BookingResponse.BookingResponseBuilder bookingResponse = BookingResponse.builder();

        bookingResponse.bookingId( booking.getId() );
        bookingResponse.event( eventToEventResponse( booking.getEvent() ) );
        bookingResponse.quantity( booking.getQuantity() );
        bookingResponse.totalPrice( booking.getTotalPrice() );

        return bookingResponse.build();
    }

    protected EventResponse eventToEventResponse(Event event) {
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
}
