package com.example.bromessenger.service;

import com.example.bromessenger.model.request.auth.SignUpRequest;
import com.example.bromessenger.model.request.auth.SigninRequest;
import com.example.bromessenger.model.resonse.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
