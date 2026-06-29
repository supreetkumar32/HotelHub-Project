package com.hotelbooking.projects.HotelHub.service;

import com.hotelbooking.projects.HotelHub.repository.RevokedTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class RevokedTokenCleanupService {

    private final RevokedTokenRepository revokedTokenRepository;

    // Runs every day at midnight — deletes expired tokens to keep the table lean
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void cleanupExpiredTokens() {
        log.info("Running revoked token cleanup at {}", LocalDateTime.now());
        revokedTokenRepository.deleteExpiredTokens(LocalDateTime.now());
        log.info("Revoked token cleanup completed");
    }
}
