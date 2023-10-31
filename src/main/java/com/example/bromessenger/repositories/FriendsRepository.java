package com.example.bromessenger.repositories;

import com.example.bromessenger.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FriendsRepository extends JpaRepository<Friend,Long> {
    Optional<Friend> findByUserIdAndFriendId(Long userId, Long friendId);
    Integer countFriendsByUserId(Long userId);

    List<Friend> findByUserId(Long userId);

}
