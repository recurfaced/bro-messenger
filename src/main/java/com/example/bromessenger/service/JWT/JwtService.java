package com.example.bromessenger.service.JWT;

import com.example.bromessenger.repositories.CustomUserDetails;


public interface JwtService {
    Long extractUserName(String token);

    String generateToken(CustomUserDetails userDetails);

    boolean isTokenValid(String token, CustomUserDetails userDetails);

    String generateRefreshToken(CustomUserDetails userDetails);

    boolean isRefreshTokenValid(String refreshToken, CustomUserDetails userDetails);
}
