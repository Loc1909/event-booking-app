package com.eventbooking.mapper;

import com.eventbooking.dto.payment.PaymentResponse;
import com.eventbooking.entity.Booking;
import com.eventbooking.entity.Payment;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-02T16:45:59+0700",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23-valhalla (Oracle Corporation)"
)
@Component
public class PaymentMapperImpl implements PaymentMapper {

    @Override
    public PaymentResponse toResponse(Payment payment) {
        if ( payment == null ) {
            return null;
        }

        PaymentResponse.PaymentResponseBuilder paymentResponse = PaymentResponse.builder();

        paymentResponse.paymentId( payment.getId() );
        paymentResponse.bookingId( paymentBookingId( payment ) );
        paymentResponse.amount( payment.getAmount() );
        paymentResponse.status( payment.getStatus() );

        return paymentResponse.build();
    }

    private Long paymentBookingId(Payment payment) {
        Booking booking = payment.getBooking();
        if ( booking == null ) {
            return null;
        }
        return booking.getId();
    }
}
