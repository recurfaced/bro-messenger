package com.example.bromessenger.JWT;

import com.example.bromessenger.model.Users;
import com.example.bromessenger.model.enums.Role;
import com.example.bromessenger.model.request.SignUpRequest;
import com.example.bromessenger.model.request.SigninRequest;
import com.example.bromessenger.model.resonse.JwtAuthenticationResponse;
import com.example.bromessenger.repositories.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Data
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        log.info("регистрация");
        Users user = new Users();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setStatus(request.isStatus());
        user.setRole(Role.User);
        Users userSaved = userRepository.save(user);
        var jwt = jwtService.generateToken(userSaved);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder()
                .token(jwt)
                .userId(user.getId())
                .build();
    }
}
