package com.hotelbooking.projects.HotelHub.repository;

import com.hotelbooking.projects.HotelHub.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
