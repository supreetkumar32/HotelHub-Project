package com.hotelbooking.projects.HotelHub.dto;

import com.hotelbooking.projects.HotelHub.entity.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ProfileUpdateRequestDto {
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
}
