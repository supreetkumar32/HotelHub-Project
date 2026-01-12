package com.hotelbooking.projects.HotelHub.repository;

import com.hotelbooking.projects.HotelHub.entity.Hotel;
import com.hotelbooking.projects.HotelHub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByOwner(User user);
}
