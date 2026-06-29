package com.hotelbooking.projects.HotelHub.repository;

import com.hotelbooking.projects.HotelHub.entity.Booking;
import com.hotelbooking.projects.HotelHub.entity.Hotel;
import com.hotelbooking.projects.HotelHub.entity.User;
import com.hotelbooking.projects.HotelHub.entity.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    Optional<Booking> findByPaymentSessionId(String sessionId);

    List<Booking> findByHotel(Hotel hotel);

    List<Booking> findByHotelAndCreatedAtBetween(Hotel hotel, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<Booking> findByUser(User user);

    @Query("""
            SELECT b FROM Booking b
            WHERE b.bookingStatus IN :statuses
            AND b.createdAt < :expiryTime
            """)
    List<Booking> findExpiredBookings(
            @Param("statuses") List<BookingStatus> statuses,
            @Param("expiryTime") LocalDateTime expiryTime
    );
}
