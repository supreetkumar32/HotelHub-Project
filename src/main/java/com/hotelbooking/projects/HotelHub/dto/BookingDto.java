package com.hotelbooking.projects.HotelHub.dto;

import com.hotelbooking.projects.HotelHub.entity.Guest;
import com.hotelbooking.projects.HotelHub.entity.Hotel;
import com.hotelbooking.projects.HotelHub.entity.Room;
import com.hotelbooking.projects.HotelHub.entity.User;
import com.hotelbooking.projects.HotelHub.entity.enums.BookingStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {

    private Long id;
    private Integer roomsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;
    private BigDecimal amount;
}
