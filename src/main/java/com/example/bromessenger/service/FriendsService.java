package com.example.bromessenger.service;

import com.example.bromessenger.model.Friend;
import com.example.bromessenger.model.resonse.CountFriendsResponse;
import com.example.bromessenger.model.resonse.ListFriendsResponse;
import com.example.bromessenger.repositories.FriendsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@Data
public class FriendsService {
    private final UserServiceImpl userService;
    private final FriendsRepository friendsRepository;
    public ResponseEntity<?> deleteFriendById(Long userId, Long friendId) {
       Optional<Friend> optionalFriends =
               friendsRepository.findByUserIdAndFriendId(userId, friendId);
       if (optionalFriends.isPresent()){
           Friend friends =optionalFriends.get();
           friendsRepository.deleteById(friends.getId());
           return ResponseEntity.ok("пользователь удален из ваших списков друзей");
       }else {
           throw new EntityNotFoundException(
                   "Friend not found with userId: " + userId + " and friend: " + friendId);
       }

    }

    public void addFriend(Long id, Long friendId) {
        Friend friends = new Friend();
        friends.setUserId(id);
        friends.setFriendId(friendId);
        friendsRepository.save(friends);
    }
    public Map<Long, String> getFriendsListById(Long userId) {
        List<Friend> friends = friendsRepository.findByUserId(userId);
        Map<Long, String> friendsMap = new HashMap<>();

        for (Friend friend : friends) {
            Long friendId = friend.getFriendId();
            String friendUsername = userService.getUsernameById(friendId);
            friendsMap.put(friendId, friendUsername);
        }
        return friendsMap;
    }
    public CountFriendsResponse countFriendsResponse(Long id){
        Integer friendsCounts = friendsRepository.countFriendsByUserId(id);
        return CountFriendsResponse.builder()
                .friendsCount(friendsCounts)
                .build();
    }

    public ListFriendsResponse listFriendsResponse(Long id){
        return ListFriendsResponse.builder()
                .getFriendsListById(getFriendsListById(id))
                .build();
    }
}
