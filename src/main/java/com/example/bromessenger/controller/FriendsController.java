package com.example.bromessenger.controller;

import com.example.bromessenger.model.resonse.CountFriendsResponse;
import com.example.bromessenger.model.resonse.ListFriendsResponse;
import com.example.bromessenger.repositories.FriendsRepository;
import com.example.bromessenger.service.FriendsService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friends")
@Slf4j
public class FriendsController {

    private final FriendsService friendsService;

    @PostMapping("/{id}")
    public void addFriends(@PathVariable Long id, @RequestParam Long friendId) {
        friendsService.addFriend(id, friendId);
    }

    @DeleteMapping("/")
    public ResponseEntity<?> deleteFriend(@RequestParam Long userId, @RequestParam Long friendId) {
        return friendsService.deleteFriendById(userId, friendId);
    }
    @GetMapping("/count/{id}")
    public CountFriendsResponse countFriends(@PathVariable Long id){
        return friendsService.countFriendsResponse(id);
    }

    @GetMapping("/list/{id}")
    public ListFriendsResponse listFriends(@PathVariable Long id){
        return friendsService.listFriendsResponse(id);
    }
}
