package com.eventbooking.event_booking_app.entity;

import com.eventbooking.event_booking_app.common.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bookings")
public class Booking {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(optional = false)
  @JoinColumn(name = "event_id")
  private Event event;

  @Column(nullable = false)
  private Integer quantity;

  @Column(name = "total_price", precision = 10, scale = 2, nullable = false)
  private BigDecimal totalPrice;

  @Enumerated(EnumType.STRING)
  private BookingStatus status = BookingStatus.PENDING;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  @OneToOne(mappedBy = "booking", cascade = CascadeType.ALL)
  private Payment payment;
}
