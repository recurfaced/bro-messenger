package com.example.bromessenger.service;

import com.example.bromessenger.model.Users;
import com.example.bromessenger.repositories.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Data
@Slf4j
public class UserServiceImpl implements UserService {
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


    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByUsername(username)
                        .orElseThrow(()-> new UsernameNotFoundException("User not found"));
            }
        };
    }
}
