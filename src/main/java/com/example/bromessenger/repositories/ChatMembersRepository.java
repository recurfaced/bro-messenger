package com.example.bromessenger.repositories;

import com.example.bromessenger.model.ChatMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatMembersRepository extends JpaRepository<ChatMember,Long> {
    Optional<ChatMember> findByUserIdAndChatId(Long userId, Long chatId);
}
