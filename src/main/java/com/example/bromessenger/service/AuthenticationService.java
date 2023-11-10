package com.example.bromessenger.service;

import com.example.bromessenger.web.request.auth.SignUpRequest;
import com.example.bromessenger.web.request.auth.SigninRequest;
import com.example.bromessenger.web.resonse.JwtAuthenticationResponse;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
