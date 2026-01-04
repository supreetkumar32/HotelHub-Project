package com.hotelbooking.projects.HotelHub.controller;

import com.hotelbooking.projects.HotelHub.dto.HotelDto;
import com.hotelbooking.projects.HotelHub.dto.HotelInfoDto;
import com.hotelbooking.projects.HotelHub.dto.HotelPriceDto;
import com.hotelbooking.projects.HotelHub.dto.HotelSearchRequest;
import com.hotelbooking.projects.HotelHub.service.HotelService;
import com.hotelbooking.projects.HotelHub.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("hotels")
@RequiredArgsConstructor
@Slf4j
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelPriceDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest){
        Page<HotelPriceDto> page=inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId){
        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
    }
}
