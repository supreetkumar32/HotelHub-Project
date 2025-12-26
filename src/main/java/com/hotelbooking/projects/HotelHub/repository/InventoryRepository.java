package com.hotelbooking.projects.HotelHub.repository;

import com.hotelbooking.projects.HotelHub.entity.Inventory;
import com.hotelbooking.projects.HotelHub.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

//    void deleteByDateAfterAndRoom(LocalDate date, Room room);
}
