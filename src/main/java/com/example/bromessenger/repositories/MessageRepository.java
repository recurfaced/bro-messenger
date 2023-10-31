package com.example.bromessenger.repositories;

import com.example.bromessenger.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {

}
