package com.example.bromessenger.repositories;


import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

public interface CustomUserDetails extends UserDetails, Serializable {
    Long getUserId();
}
