package com.hotelbooking.projects.HotelHub.dto;

import com.hotelbooking.projects.HotelHub.entity.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String name;
    private Gender gender;
    private LocalDate dateOfBirth;
}
