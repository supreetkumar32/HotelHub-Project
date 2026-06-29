package com.hotelbooking.projects.HotelHub.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HolidayService {

    private static final String HOLIDAY_API_URL =
            "https://date.nager.at/api/v3/PublicHolidays/{year}/IN";

    private final RestTemplate restTemplate = new RestTemplate();

    // Cache: year -> set of holiday dates (fetched once per year, reused for all calculations)
    private final Map<Integer, Set<LocalDate>> holidayCache = new ConcurrentHashMap<>();

    /**
     * Returns true if the given date is a public holiday in India.
     * Fetches from Nager.Date API once per year and caches the result.
     * Fails safely — returns false if the API call fails.
     */
    public boolean isHoliday(LocalDate date) {
        Set<LocalDate> holidays = holidayCache.computeIfAbsent(date.getYear(), this::fetchHolidays);
        return holidays.contains(date);
    }

    private Set<LocalDate> fetchHolidays(int year) {
        try {
            HolidayResponse[] response = restTemplate.getForObject(
                    HOLIDAY_API_URL, HolidayResponse[].class, year);

            if (response == null || response.length == 0) {
                log.warn("Holiday API returned empty response for year {}", year);
                return Collections.emptySet();
            }

            log.info("Fetched {} public holidays for year {} from Nager.Date API", response.length, year);

            return Arrays.stream(response)
                    .map(h -> LocalDate.parse(h.getDate()))
                    .collect(Collectors.toSet());

        } catch (Exception e) {
            log.error("Failed to fetch public holidays for year {} — holiday pricing will not be applied. Error: {}",
                    year, e.getMessage());
            return Collections.emptySet();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class HolidayResponse {
        private String date; // "2026-01-26"
        private String localName;
        private String name;
    }
}
