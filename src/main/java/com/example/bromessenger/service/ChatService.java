package com.example.bromessenger.service;


import com.example.bromessenger.model.ChatMembers;
import com.example.bromessenger.repositories.ChatMembersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@Data
public class ChatService {
    private final ChatMembersRepository chatMembersRepository;
    public void deleteMemberFromChat(Long userId, Long chatId) {
        Optional<ChatMembers> chatMembersOptional = chatMembersRepository.findByUserIdAndChatId(userId, chatId);
        if (chatMembersOptional.isPresent()){
            ChatMembers chatMembers = chatMembersOptional.get();
            log.info(chatMembers.getChatMemberId().toString());
            chatMembersRepository.deleteById(chatMembers.getChatMemberId());
        }else {
            throw new EntityNotFoundException(
                    "Chat member not found with userId: " + userId + " and chatId: " + chatId);
        }
    }
}
