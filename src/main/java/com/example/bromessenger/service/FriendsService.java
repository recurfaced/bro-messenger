package com.example.bromessenger.service;

import com.example.bromessenger.model.Friends;
import com.example.bromessenger.repositories.FriendsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@Data
public class FriendsService {
    private final FriendsRepository friendsRepository;
    public ResponseEntity<?> deleteFriendById(Long userId, Long friendId) {
       Optional<Friends> optionalFriends =
               friendsRepository.findByUserIdAndFriendId(userId, friendId);
       if (optionalFriends.isPresent()){
           Friends friends =optionalFriends.get();
           friendsRepository.deleteById(friends.getId());
           return ResponseEntity.ok("пользователь удален из ваших списков друзей");
       }else {
           throw new EntityNotFoundException(
                   "Friend not found with userId: " + userId + " and friend: " + friendId);
       }

    }

    public ResponseEntity<?> addFriend(Long id, Long friendId) {
        Friends friends = new Friends();
        friends.setUserId(id);
        friends.setFriendId(friendId);
        friendsRepository.save(friends);
        return ResponseEntity.ok("друг добавлен");
    }
}
