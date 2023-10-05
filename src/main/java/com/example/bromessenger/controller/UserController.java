package com.example.bromessenger.controller;


import com.example.bromessenger.model.Friends;
import com.example.bromessenger.model.Users;
import com.example.bromessenger.repositories.FriendsRepository;
import com.example.bromessenger.repositories.UserRepository;
import com.example.bromessenger.service.UserService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Data
@Slf4j
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/")
    public List<Users> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with this id not found"));
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id,@RequestBody Users userRequest){
        return userService.editUserInfo(id,userRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ошибка удаления"));
        userRepository.delete(user);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/create/user")
    public ResponseEntity<Users> createUser(@RequestBody Users userRequest){

        Users user = new Users();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setStatus(userRequest.isStatus());

        Users userSaved = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);

    }

}
