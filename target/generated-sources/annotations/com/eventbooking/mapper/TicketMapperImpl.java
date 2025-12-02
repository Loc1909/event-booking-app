package com.eventbooking.mapper;

import com.eventbooking.dto.booking.TicketResponse;
import com.eventbooking.dto.event.EventInfo;
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
public class TicketMapperImpl implements TicketMapper {

    @Override
    public TicketResponse toTicketResponse(Booking booking) {
        if ( booking == null ) {
            return null;
        }

        TicketResponse.TicketResponseBuilder ticketResponse = TicketResponse.builder();

        ticketResponse.ticketId( booking.getId() );
        ticketResponse.event( toEventInfo( booking.getEvent() ) );
        ticketResponse.quantity( booking.getQuantity() );

        ticketResponse.status( booking.getStatus().name() );

        return ticketResponse.build();
    }

    @Override
    public EventInfo toEventInfo(Event event) {
        if ( event == null ) {
            return null;
        }

        EventInfo.EventInfoBuilder eventInfo = EventInfo.builder();

        eventInfo.id( event.getId() );
        eventInfo.title( event.getTitle() );
        eventInfo.dateTime( event.getDateTime() );
        eventInfo.location( event.getLocation() );

        return eventInfo.build();
    }
}
