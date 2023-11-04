package com.example.bromessenger.service;

import com.example.bromessenger.model.User;
import com.example.bromessenger.model.dto.UserAuthDto;
import com.example.bromessenger.model.mapper.UserMapperImpl;
import com.example.bromessenger.repositories.FriendsRepository;
import com.example.bromessenger.repositories.UserRepository;
import com.example.bromessenger.service.JWT.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Data
@AllArgsConstructor
@Slf4j
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private UserMapperImpl userMapper;
    private final FriendsRepository friendsRepository;
    public UserAuthDto editUserInfo(HttpServletRequest request, User userRequest) {
        Long id = getUserIdByJWT(request);
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setUsername(userRequest.getUsername());
                    return userMapper.EditModelToDto(userRepository.save(existingUser));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Ошибка изменения"));
    }


    public String getUsernameById(Long friendId) {
        Optional<User> userOptional = userRepository.findById(friendId);
        return userOptional.map(User::getUsername).orElse(null);
    }
    public List<User> getAllUserService(){
        return userRepository.findAll();
    }
    public UserAuthDto getUserByIdService(HttpServletRequest request){
        LocalDateTime lastLogin = LocalDateTime.now();
        Long id = getUserIdByJWT(request);
         return userRepository.findById(id)
                .map(existingUser->{
                    existingUser.setFriends(friendsRepository.countFriendsByUserId(id));
                    existingUser.setLastLogin(lastLogin);
                    return userMapper.EditModelToDto(existingUser);
                })
                .orElseThrow(() -> new ResourceNotFoundException("User with this id not found"));
    }
    public void deleteUserService(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ошибка секса"));
        userRepository.delete(user);
    }

    public Long getUserIdByJWT(HttpServletRequest request){
        String jwtToken = (String) request.getAttribute("jwtToken");
        return jwtService.extractUserName(jwtToken);
    }

}
