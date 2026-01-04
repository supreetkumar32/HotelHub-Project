package com.hotelbooking.projects.HotelHub.dto;

import com.hotelbooking.projects.HotelHub.entity.User;
import com.hotelbooking.projects.HotelHub.entity.enums.Gender;
import lombok.Data;

@Data
public class GuestDto {

    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
