package com.hotelbooking.projects.HotelHub.service;

import com.hotelbooking.projects.HotelHub.entity.Booking;
import com.hotelbooking.projects.HotelHub.entity.enums.BookingStatus;
import com.hotelbooking.projects.HotelHub.repository.BookingRepository;
import com.hotelbooking.projects.HotelHub.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingCleanupService {

    private final BookingRepository bookingRepository;
    private final InventoryRepository inventoryRepository;

    private static final int BOOKING_EXPIRY_MINUTES = 100;

    // Runs every 10 minutes
    @Scheduled(cron = "0 */10 * * * *")
    @Transactional
    public void releaseExpiredBookings() {
        LocalDateTime expiryTime = LocalDateTime.now().minusMinutes(BOOKING_EXPIRY_MINUTES);

        List<BookingStatus> pendingStatuses = List.of(
                BookingStatus.RESERVED,
                BookingStatus.GUESTS_ADDED,
                BookingStatus.PAYMENT_PENDING
        );

        List<Booking> expiredBookings = bookingRepository.findExpiredBookings(pendingStatuses, expiryTime);

        if (expiredBookings.isEmpty()) {
            log.info("No expired bookings found at {}", LocalDateTime.now());
            return;
        }

        log.info("Found {} expired bookings to release", expiredBookings.size());

        for (Booking booking : expiredBookings) {
            try {
                // 1. Release the reserved inventory back
                inventoryRepository.releaseReservation(
                        booking.getRoom().getId(),
                        booking.getCheckInDate(),
                        booking.getCheckOutDate(),
                        booking.getRoomsCount()
                );

                // 2. Mark booking as EXPIRED
                booking.setBookingStatus(BookingStatus.EXPIRED);
                bookingRepository.save(booking);

                log.info("Released expired booking ID: {} for room ID: {}, dates: {} to {}",
                        booking.getId(),
                        booking.getRoom().getId(),
                        booking.getCheckInDate(),
                        booking.getCheckOutDate());

            } catch (Exception e) {
                log.error("Failed to release expired booking ID: {}. Error: {}", booking.getId(), e.getMessage());
            }
        }

        log.info("Completed cleanup: {} expired bookings released", expiredBookings.size());
    }
}
