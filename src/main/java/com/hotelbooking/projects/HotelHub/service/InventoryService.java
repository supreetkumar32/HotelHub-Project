package com.hotelbooking.projects.HotelHub.service;

import com.hotelbooking.projects.HotelHub.dto.*;
import com.hotelbooking.projects.HotelHub.entity.HotelMinPrice;
import com.hotelbooking.projects.HotelHub.entity.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);

    List<InventoryDto> getAllInventoryByRoom(Long roomId);

    void updateInventory(Long roomId, UpdateInventoryRequestDto updateInventoryRequestDto);
}
