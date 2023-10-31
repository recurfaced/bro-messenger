package com.example.bromessenger.service;

import com.example.bromessenger.model.User;
import com.example.bromessenger.repositories.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Data
@Slf4j
public class UserServiceImpl {
    private final UserRepository userRepository;
    public User editUserInfo(Long id, User userRequest) {
        User users = userRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Ошибка изменения"));
        users.setUsername(userRequest.getUsername());
        users.setEmail(userRequest.getEmail());
        users.setPassword(userRequest.getPassword());
        userRepository.save(users);
        return users;
    }

    public String getUsernameById(Long friendId) {
        Optional<User> userOptional = userRepository.findById(friendId);
        return userOptional.map(User::getUsername).orElse(null);
    }
    public List<User> getAllUserService(){
        return userRepository.findAll();
    }
    public User getUserByIdService(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with this id not found"));
    }
    public void deleteUserService(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ошибка удаления"));
        userRepository.delete(user);
    }
}
