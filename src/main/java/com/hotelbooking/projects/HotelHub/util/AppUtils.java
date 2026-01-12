package com.hotelbooking.projects.HotelHub.util;

import com.hotelbooking.projects.HotelHub.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class AppUtils {
    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
