package com.eventbooking.event_booking_app.entity;

import com.eventbooking.event_booking_app.common.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password; // BCrypt hashed

  private String avatar;

  @Enumerated(EnumType.STRING)
  private Role role = Role.USER;

  @Column(name = "created_at", updatable = false)
  private LocalDateTime createdAt;

  // Quan hệ
  @OneToMany(mappedBy = "user")
  private List<Booking> bookings;

  @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  private Reminder reminder;
}
