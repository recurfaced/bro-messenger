package com.example.bromessenger.repositories;

import com.example.bromessenger.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
}
