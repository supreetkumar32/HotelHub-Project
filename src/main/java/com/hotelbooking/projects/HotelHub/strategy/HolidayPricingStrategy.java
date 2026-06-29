package com.hotelbooking.projects.HotelHub.strategy;

import com.hotelbooking.projects.HotelHub.entity.Inventory;
import com.hotelbooking.projects.HotelHub.service.HolidayService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class HolidayPricingStrategy implements PricingStrategy {

    private final PricingStrategy wrapped;
    private final HolidayService holidayService;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);
        if (holidayService.isHoliday(inventory.getDate())) {
            price = price.multiply(BigDecimal.valueOf(1.25));
        }
        return price;
    }
}
