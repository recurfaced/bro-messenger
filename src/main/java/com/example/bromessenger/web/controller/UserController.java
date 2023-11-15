package com.example.bromessenger.web.controller;

import com.example.bromessenger.model.dto.UserAuthDto;
import com.example.bromessenger.model.mapper.UserMapperImpl;
import com.example.bromessenger.security.xxs.XssValidatorSignUp;
import com.example.bromessenger.service.AuthenticationService;
import com.example.bromessenger.model.User;
import com.example.bromessenger.service.AuthenticationServiceImpl;
import com.example.bromessenger.web.request.auth.SignUpRequest;
import com.example.bromessenger.web.request.auth.SigninRequest;
import com.example.bromessenger.web.resonse.JwtAuthenticationResponse;
import com.example.bromessenger.service.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Data
@Slf4j
final class UserController {

    private final UserServiceImpl userService;
    private final AuthenticationService authenticationService;
    private final UserMapperImpl userMapper;

    @GetMapping("/all")
    public List<User> getAllUser() {
        return userService.getAllUserService();
    }

    @GetMapping("/")
    public UserAuthDto getUserById(HttpServletRequest request) {
        return userService.getUserByIdService(request);
    }

    @PutMapping("/")
    public UserAuthDto updateUser(@RequestBody UserAuthDto userRequest, HttpServletRequest request) {
        return userService.editUserInfo(request, userMapper.EditDtoToModel(userRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserService(id);
    }


    @PostMapping("/signup")
    @CrossOrigin("http://localhost:8080")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        if (
            XssValidatorSignUp.checkForXss(request)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        JwtAuthenticationResponse jwtResponse = authenticationService.signup(request);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/signin")
    @CrossOrigin("http://localhost:8080")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request,
                                                            HttpServletResponse response) {
        JwtAuthenticationResponse authenticationResponse = authenticationService.signin(request);

        Cookie cookie = new Cookie("token", authenticationResponse.getAccessToken());
        cookie.setPath("/");
        response.addCookie(cookie);

        Cookie refreshTokenCookie = new Cookie("refreshToken", authenticationResponse.getRefreshToken());
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.ok(authenticationResponse);
    }


    @PostMapping("/refresh")
    @SneakyThrows
    public void refreshToken(HttpServletRequest request,
                             HttpServletResponse response){
        userService.refreshToken(request,response);
    }
}
