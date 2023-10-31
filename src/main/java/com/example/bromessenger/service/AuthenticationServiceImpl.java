package com.example.bromessenger.service;

import com.example.bromessenger.service.JWT.JwtService;
import com.example.bromessenger.model.User;
import com.example.bromessenger.model.enums.Role;
import com.example.bromessenger.model.request.auth.SignUpRequest;
import com.example.bromessenger.model.request.auth.SigninRequest;
import com.example.bromessenger.model.resonse.JwtAuthenticationResponse;
import com.example.bromessenger.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        log.info("регистрация");
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setStatus(request.isStatus());
        user.setRole(Role.User);
        User userSaved = userRepository.save(user);
        var jwt = jwtService.generateToken(userSaved);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {

        /*authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword())
        );*/

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));

        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder()
                .token(jwt)
                .userId(user.getId())
                .build();
    }
}
