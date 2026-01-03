package com.hotelbooking.projects.HotelHub.service;

import com.hotelbooking.projects.HotelHub.entity.Room;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);
}
