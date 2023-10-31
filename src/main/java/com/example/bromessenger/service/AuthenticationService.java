package com.example.bromessenger.service;

import com.example.bromessenger.model.request.SignUpRequest;
import com.example.bromessenger.model.request.SigninRequest;
import com.example.bromessenger.model.resonse.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
