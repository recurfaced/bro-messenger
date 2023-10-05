package com.example.bromessenger.repositories;

import com.example.bromessenger.model.ChatMembers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatMembersRepository extends JpaRepository<ChatMembers,Long> {
    Optional<ChatMembers> findByUserIdAndChatId(Long userId, Long chatId);
}
