package com.example.bromessenger.repositories;

import com.example.bromessenger.model.Chats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatsRepository extends JpaRepository<Chats,Long> {
}
