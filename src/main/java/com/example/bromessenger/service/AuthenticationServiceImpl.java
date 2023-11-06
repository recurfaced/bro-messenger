package com.example.bromessenger.service;

import com.example.bromessenger.model.Token;
import com.example.bromessenger.repositories.TokenRepository;
import com.example.bromessenger.service.JWT.JwtService;
import com.example.bromessenger.model.User;
import com.example.bromessenger.model.enums.Role;
import com.example.bromessenger.web.request.auth.SignUpRequest;
import com.example.bromessenger.web.request.auth.SigninRequest;
import com.example.bromessenger.web.resonse.JwtAuthenticationResponse;
import com.example.bromessenger.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public final class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .status(request.isStatus())
                .role(Role.User)
                .build();
        User userSaved = userRepository.save(user);
        String accessJwt = jwtService.generateToken(userSaved);
        String refreshJwt =jwtService.generateRefreshToken(userSaved);
        saveUserToken(userSaved, refreshJwt);
        return JwtAuthenticationResponse.builder()
                .refreshToken(refreshJwt)
                .accessToken(accessJwt)
                .build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        String accessJwt = jwtService.generateToken(user);
        String refreshJwt = jwtService.generateRefreshToken(user);


        return JwtAuthenticationResponse.builder()
                .refreshToken(refreshJwt)
                .accessToken(accessJwt)
                .build();
    }

    private void saveUserToken(User user, String refreshToken) {
        var token = Token.builder()
                .user(user)
                .token(refreshToken)
                .build();
        tokenRepository.save(token);
    }

    /*private AuthenticationServiceImpl(  UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }*/
}
