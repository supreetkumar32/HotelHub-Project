package com.hotelbooking.projects.HotelHub.service;

import com.hotelbooking.projects.HotelHub.entity.Booking;

public interface CheckoutService {

    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);
}
