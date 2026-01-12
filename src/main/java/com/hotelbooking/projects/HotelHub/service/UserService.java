package com.hotelbooking.projects.HotelHub.service;

import com.hotelbooking.projects.HotelHub.dto.ProfileUpdateRequestDto;
import com.hotelbooking.projects.HotelHub.dto.UserDto;
import com.hotelbooking.projects.HotelHub.entity.User;

public interface UserService {

    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();
}
