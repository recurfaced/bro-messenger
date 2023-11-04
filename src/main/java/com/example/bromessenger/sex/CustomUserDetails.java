package com.example.bromessenger.sex;


import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

public interface CustomUserDetails extends UserDetails, Serializable {
    Long getUserId();
}
