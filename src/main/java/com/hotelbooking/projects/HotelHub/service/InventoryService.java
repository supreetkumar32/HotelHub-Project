package com.hotelbooking.projects.HotelHub.service;

import com.hotelbooking.projects.HotelHub.dto.HotelDto;
import com.hotelbooking.projects.HotelHub.dto.HotelSearchRequest;
import com.hotelbooking.projects.HotelHub.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
