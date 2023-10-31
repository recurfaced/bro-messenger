package com.example.bromessenger.controller;

import com.example.bromessenger.service.AuthenticationService;
import com.example.bromessenger.model.User;
import com.example.bromessenger.model.request.SignUpRequest;
import com.example.bromessenger.model.request.SigninRequest;
import com.example.bromessenger.model.resonse.JwtAuthenticationResponse;
import com.example.bromessenger.service.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Data
@Slf4j
public class UserController {
    private final UserServiceImpl userService;
    private final AuthenticationService authenticationService;

    @GetMapping("/")
    public List<User> getAllUser() {
        return userService.getAllUserService();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserByIdService(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User userRequest) {
        return userService.editUserInfo(id, userRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserService(id);
    }


    @PostMapping("/signup")
    @CrossOrigin("http://localhost:8080")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        JwtAuthenticationResponse jwtResponse = authenticationService.signup(request);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signin")
    @CrossOrigin("http://localhost:8080")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request, HttpServletResponse response) {
        JwtAuthenticationResponse authenticationResponse = authenticationService.signin(request);

        Cookie cookie = new Cookie("token", authenticationResponse.getToken());
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(authenticationResponse);
    }
}
