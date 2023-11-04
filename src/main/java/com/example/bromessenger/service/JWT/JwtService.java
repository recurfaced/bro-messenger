package com.example.bromessenger.service.JWT;

import com.example.bromessenger.sex.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;


public interface JwtService {
    Long extractUserName(String token);

    String generateToken(CustomUserDetails userDetails);

    boolean isTokenValid(String token, CustomUserDetails userDetails);
}
