package com.eventbooking.controller;

import com.eventbooking.common.base.BaseResponse;
import com.eventbooking.dto.booking.TicketResponse;
import com.eventbooking.exception.UnauthorizedException;
import com.eventbooking.security.UserPrincipal;
import com.eventbooking.service.TicketService;
import com.eventbooking.util.ApiResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

  private final TicketService ticketService;

  @GetMapping
  public ResponseEntity<BaseResponse<List<TicketResponse>>> getMyTickets(
      @AuthenticationPrincipal UserPrincipal currentUser) {

    if (currentUser == null) {
      throw new UnauthorizedException(
          "Unauthorized – Please login to access this resource");
    }

    List<TicketResponse> tickets = ticketService.getMyTickets(currentUser.getId());

    return ResponseEntity.ok(
        ApiResponseBuilder.success("Tickets fetched successfully", tickets));
  }
}

