package com.example.bromessenger.service;

import com.example.bromessenger.model.Users;
import com.example.bromessenger.repositories.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Data
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    public ResponseEntity<?> editUserInfo(Long id, Users userRequest) {
        Users users = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Ошибка изменения"));
        users.setUsername(userRequest.getUsername());
        users.setEmail(userRequest.getEmail());
        users.setPassword(userRequest.getPassword());
        userRepository.save(users);
        return ResponseEntity.ok("данные о пользователе были обновлены");
    }
}
