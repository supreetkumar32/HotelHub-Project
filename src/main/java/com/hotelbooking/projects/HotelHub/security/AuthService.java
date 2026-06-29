package com.hotelbooking.projects.HotelHub.security;

import com.hotelbooking.projects.HotelHub.dto.LoginDto;
import com.hotelbooking.projects.HotelHub.dto.SignUpRequestDto;
import com.hotelbooking.projects.HotelHub.dto.UserDto;
import com.hotelbooking.projects.HotelHub.entity.RevokedToken;
import com.hotelbooking.projects.HotelHub.entity.User;
import com.hotelbooking.projects.HotelHub.entity.enums.Role;
import com.hotelbooking.projects.HotelHub.exception.ResourceNotFoundException;
import com.hotelbooking.projects.HotelHub.exception.UnAuthorisedException;
import com.hotelbooking.projects.HotelHub.repository.RevokedTokenRepository;
import com.hotelbooking.projects.HotelHub.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final RevokedTokenRepository revokedTokenRepository;

    public UserDto signUp(SignUpRequestDto signUpRequestDto) {

        User user = userRepository.findByEmail(signUpRequestDto.getEmail()).orElse(null);

        if (user != null) {
            throw new RuntimeException("User is already present with same email id");
        }

        User newUser = modelMapper.map(signUpRequestDto, User.class);
        newUser.setRoles(Set.of(Role.GUEST));
        newUser.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        newUser = userRepository.save(newUser);

        return modelMapper.map(newUser, UserDto.class);
    }

    public String[] login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(), loginDto.getPassword()
        ));

        User user = (User) authentication.getPrincipal();

        String[] arr = new String[2];
        arr[0] = jwtService.generateAccessToken(user);
        arr[1] = jwtService.generateRefreshToken(user);

        return arr;
    }

    public String refreshToken(String refreshToken) {
        if (revokedTokenRepository.existsByToken(refreshToken)) {
            throw new UnAuthorisedException("Refresh token has been revoked. Please login again.");
        }

        Long id = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return jwtService.generateAccessToken(user);
    }

    public void logout(String refreshToken, HttpServletResponse response) {
        // Blocklist the refresh token in DB so it can never be reused
        if (refreshToken != null && !refreshToken.isBlank() && !revokedTokenRepository.existsByToken(refreshToken)) {
            LocalDateTime expiresAt = jwtService.getExpiryFromToken(refreshToken);
            revokedTokenRepository.save(RevokedToken.builder()
                    .token(refreshToken)
                    .expiresAt(expiresAt)
                    .build());
        }

        // Clear the cookie from browser
        Cookie expiredCookie = new Cookie("refreshToken", null);
        expiredCookie.setHttpOnly(true);
        expiredCookie.setMaxAge(0);
        expiredCookie.setPath("/");
        response.addCookie(expiredCookie);
    }
}
