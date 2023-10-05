package com.example.bromessenger.repositories;

import com.example.bromessenger.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Messages,Long> {

}
