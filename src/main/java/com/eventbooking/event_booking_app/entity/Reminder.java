package com.eventbooking.event_booking_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "reminders")
public class Reminder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(optional = false)
  @JoinColumn(name = "user_id", unique = true)
  private User user;

  @Column(name = "event_reminder")
  private Boolean eventReminder = true;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;
}
