package com.example.bromessenger.service;


import com.example.bromessenger.model.Chat;
import com.example.bromessenger.model.ChatMember;
import com.example.bromessenger.repositories.ChatMembersRepository;
import com.example.bromessenger.repositories.ChatsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
    private final ChatMembersRepository chatMembersRepository;
    private final ChatsRepository chatsRepository;
    public void deleteMemberFromChat(Long userId, Long chatId) {
        Optional<ChatMember> chatMembersOptional = chatMembersRepository.findByUserIdAndChatId(userId, chatId);
        if (chatMembersOptional.isPresent()){
            ChatMember chatMembers = chatMembersOptional.get();
            log.info(chatMembers.getChatMemberId().toString());
            chatMembersRepository.deleteById(chatMembers.getChatMemberId());
        }else {
            throw new EntityNotFoundException(
                    "Chat member not found with userId: " + userId + " and chatId: " + chatId);
        }
    }

    public void createChat(Chat chat){
        chatsRepository.save(chat);
    }
    public void createChatMembers(ChatMember chatMembersRequest){
        chatMembersRepository.save(chatMembersRequest);
    }
}
