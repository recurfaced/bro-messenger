package com.example.bromessenger.web.controller;

import com.example.bromessenger.service.FriendsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/friends")
@Slf4j
final class FriendsController {

    private final FriendsService friendsService;

    @PostMapping("/{friendId}")
    public void addFriends(@PathVariable Long friendId,
                           HttpServletRequest request) {
        friendsService.addFriend(request,friendId);
    }

    @DeleteMapping("/{friendId}")
    public void deleteFriend(HttpServletRequest request,
                             @PathVariable Long friendId) {
        friendsService.deleteFriendById(request, friendId);
    }
    //TODO
    @GetMapping("/list")
    public Map<Long, String> listFriends(HttpServletRequest request){
        return friendsService.getFriendsListById(request);
    }
    @GetMapping("/request")
    public Map<Long, String> requestFriends(HttpServletRequest request){
        return friendsService.getFriendsRequestListById(request);
    }

    @PostMapping("/access-friends/{friendId}")
    public void accessFriend(HttpServletRequest request,
                                            @PathVariable Long friendId){
        friendsService.accessFriend(request, friendId);
    }

    @GetMapping("/search/{username}")
    public Map<Long, String> searchUser(@PathVariable String username){
        return friendsService.search(username);
    }
}
