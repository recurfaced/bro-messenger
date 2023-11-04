package com.example.bromessenger.repositories;

import com.example.bromessenger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    /*Optional<User> findByUsername(String email);*/

    @Query(value = "SELECT u FROM User u WHERE u.username = :username")
    List<User> findByUsername(@Param("username") String username);


}
