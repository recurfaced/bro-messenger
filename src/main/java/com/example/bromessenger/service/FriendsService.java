package com.example.bromessenger.service;

import com.example.bromessenger.model.Friend;
import com.example.bromessenger.model.User;
import com.example.bromessenger.repositories.FriendsRepository;
import com.example.bromessenger.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public final class FriendsService {

    private final UserServiceImpl userService;
    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;

    public void deleteFriendById(HttpServletRequest request, Long friendId) {
        Long userId = userService.getUserIdByJWT(request);
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<User> friendOptional = userRepository.findById(friendId);

        if (userOptional.isPresent() && friendOptional.isPresent()) {
            User user = userOptional.get();
            User friend = friendOptional.get();

            Friend requestToAccessFriend = friendsRepository.findByUserAndFriendId(user, friendId);
            if (requestToAccessFriend != null) {
                requestToAccessFriend.setAccept(false);
                friendsRepository.save(requestToAccessFriend);
            } else {
                throw new ResourceNotFoundException("denied request to delete friend");
            }
        } else {
            throw new ResourceNotFoundException("User or Friend not found");
        }

    }

    public void addFriend(HttpServletRequest request, Long friendId) {
        Long userId = userService.getUserIdByJWT(request);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        User friendUser = userRepository.findById(friendId)
                .orElseThrow(() -> new ResourceNotFoundException("Friend not found"));

        if (user.getFriends().stream().anyMatch(friend -> friend.getFriendId().equals(friendId))) {
            throw new IllegalArgumentException("Friend request already exists");
        }

        Friend friendRequest = new Friend(friendId, false, user);

        user.getFriends().add(friendRequest);

        userRepository.save(user);
    }
    public Map<Long, String> getFriendsListById(HttpServletRequest request) {
        List<Friend> friends = friendsRepository.findByUserIdAndAccept(
                userService.getUserIdByJWT(request));
        return getLongStringMap(friends);
    }

    private Map<Long, String> getLongStringMap(List<Friend> friends) {
        Map<Long, String> friendsMap = new HashMap<>();

        for (Friend friend : friends) {
            Long friendId = friend.getFriendId();
            String friendUsername = userService.getUsernameById(friendId);
            friendsMap.put(friendId, friendUsername);
        }
        return friendsMap;
    }

    public Map<Long, String> getFriendsRequestListById(HttpServletRequest request) {

        List<Friend> requestList = friendsRepository.countFriendsByUserId(
                userService.getUserIdByJWT(request)
        );
        return getLongStringMap(requestList);
    }

    public Map<Long, String> search(String username) {
        Map<Long, String> friendsMap = new HashMap<>();
        List<User> user = userRepository.findByUsername(username);
        for (User users: user) {
            Long userId = users.getId();
            String usernameSearch = userService.getUsernameById(userId);
            friendsMap.put(userId,usernameSearch);
        }
        return friendsMap;
    }

    public void accessFriend(HttpServletRequest request, Long friendId) {
        Long userId = userService.getUserIdByJWT(request);
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<User> friendOptional = userRepository.findById(friendId);

        if (userOptional.isPresent() && friendOptional.isPresent()) {
            User user = userOptional.get();
            User friend = friendOptional.get();

            Friend requestToAccessFriend = friendsRepository.findByUserAndFriendId(user, friendId);
            if (requestToAccessFriend != null) {
                requestToAccessFriend.setAccept(true);
                friendsRepository.save(requestToAccessFriend);
            } else {
                throw new ResourceNotFoundException("denied request to delete friend");
            }
        } else {
            throw new ResourceNotFoundException("User or Friend not found");
        }
    }


    /*public ListFriendsResponse listFriendsResponse(HttpServletRequest request){
        Long id = userService.getUserIdByJWT(request);
        return ListFriendsResponse.builder()
                .getFriendsListById(getFriendsListById(id))
                .build();
    }

    public ListFriendsResponse friendsResponse(HttpServletRequest request){
        Long id = userService.getUserIdByJWT(request);
        return ListFriendsResponse.builder()
                .getFriendsListById(getFriendsRequestListById(id))
                .build();
    }*/
}
