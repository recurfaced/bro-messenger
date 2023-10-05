package com.example.bromessenger.repositories;

import com.example.bromessenger.model.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendsRepository extends JpaRepository<Friends,Long> {
    Optional<Friends> findByUserIdAndFriendId(Long userId,Long friendId);
}