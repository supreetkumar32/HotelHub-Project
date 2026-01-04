package com.hotelbooking.projects.HotelHub.strategy;

import com.hotelbooking.projects.HotelHub.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);
}
