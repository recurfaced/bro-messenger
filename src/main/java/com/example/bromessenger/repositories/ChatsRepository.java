package com.example.bromessenger.repositories;

import com.example.bromessenger.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatsRepository extends JpaRepository<Chat,Long> {
}
