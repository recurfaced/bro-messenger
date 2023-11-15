package com.example.bromessenger.repositories;

import com.example.bromessenger.model.Friend;
import com.example.bromessenger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendsRepository extends JpaRepository<Friend,Long> {
    Optional<Friend> findByUserIdAndFriendId(Long userId, Long friendId);
    @Query( value = "SELECT u FROM Friend u WHERE u.accept = false")
    List<Friend> countFriendsByUserId(Long userId);

    @Query( value = "SELECT u FROM Friend u WHERE u.accept = true")
    List<Friend> findByUserIdAndAccept(Long userId);

    List<Friend> findByUserId(Long userId);

    @Query("SELECT f FROM Friend f WHERE f.user = :user AND f.friendId = :friendId")
    Friend findByUserAndFriendId(@Param("user") User user, @Param("friendId") Long friendId);


}
