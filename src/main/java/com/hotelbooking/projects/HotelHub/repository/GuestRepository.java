package com.hotelbooking.projects.HotelHub.repository;

import com.hotelbooking.projects.HotelHub.entity.Guest;
import com.hotelbooking.projects.HotelHub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestRepository extends JpaRepository<Guest,Long> {

    List<Guest> findByUser(User user);
}
