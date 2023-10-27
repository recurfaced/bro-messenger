package com.example.bromessenger.controller;

import com.example.bromessenger.model.Friends;
import com.example.bromessenger.model.resonse.CountFriendsResponse;
import com.example.bromessenger.model.resonse.ListFriendsResponse;
import com.example.bromessenger.repositories.FriendsRepository;
import com.example.bromessenger.service.FriendsService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Data
@RequestMapping("/api/friends")
@Slf4j
public class FriendsController {

    private final FriendsRepository friendsRepository;
    private final FriendsService friendsService;

    @PostMapping("/add/friends/{id}")
    public ResponseEntity<?> addFriends(@PathVariable Long id, @RequestParam Long friendId) {
        return friendsService.addFriend(id, friendId);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFriend(@RequestParam Long userId, @RequestParam Long friendId) {
        return friendsService.deleteFriendById(userId, friendId);
    }
    @GetMapping("/count/{id}")
    public CountFriendsResponse countFriends(@PathVariable Long id){
        Integer friendsCounts = friendsRepository.countFriendsByUserId(id);
        return CountFriendsResponse.builder()
                .friendsCount(friendsCounts)
                .build();
    }

    @GetMapping("/list/{id}")
    public ListFriendsResponse listFriends(@PathVariable Long id){
        return ListFriendsResponse.builder()
                .getFriendsListById(friendsService.getFriendsListById(id))
                .build();
    }
}
