package com.hotelbooking.projects.HotelHub.controller;

import com.hotelbooking.projects.HotelHub.dto.BookingDto;
import com.hotelbooking.projects.HotelHub.dto.ProfileUpdateRequestDto;
import com.hotelbooking.projects.HotelHub.dto.UserDto;
import com.hotelbooking.projects.HotelHub.service.BookingService;
import com.hotelbooking.projects.HotelHub.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BookingService bookingService;

    @PatchMapping("/profile")
    public ResponseEntity<Void> updateProfile(@RequestBody ProfileUpdateRequestDto profileUpdateRequestDto) {
        userService.updateProfile(profileUpdateRequestDto);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/myBookings")
    public ResponseEntity<List<BookingDto>> getMyBookings() {
        return ResponseEntity.ok(bookingService.getMyBookings());
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getMyProfile() {
        return ResponseEntity.ok(userService.getMyProfile());
    }


}
