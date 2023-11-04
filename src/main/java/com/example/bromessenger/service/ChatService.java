package com.example.bromessenger.service;


import com.example.bromessenger.model.Chat;
import com.example.bromessenger.model.ChatMember;
import com.example.bromessenger.model.User;
import com.example.bromessenger.model.enums.TypeChat;
import com.example.bromessenger.web.request.chat.CreateChatRequest;
import com.example.bromessenger.repositories.ChatMembersRepository;
import com.example.bromessenger.repositories.ChatsRepository;
import com.example.bromessenger.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
    private final ChatMembersRepository chatMembersRepository;
    private final ChatsRepository chatsRepository;
    private final UserRepository userRepository;
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

    public void createChat(CreateChatRequest createChatRequest){
        log.info(createChatRequest.toString());
        User user = userRepository.findById(createChatRequest.getUserId())
                .orElseThrow(()->new ResourceNotFoundException("user not found"));
        User friend = userRepository.findById(createChatRequest.getFriendId())
                .orElseThrow(()->new ResourceNotFoundException("user not found"));



        Chat chat = new Chat();
        chat.setName("лс");
        chat.setTypeChat(TypeChat.PRIVATE);
        chatsRepository.save(chat);

        ChatMember chatMember1 = new ChatMember();
        ChatMember chatMember2 = new ChatMember();
        chatMember1.setChat(chat);
        chatMember1.setUser(user);
        chatMembersRepository.save(chatMember1);

        chatMember2.setChat(chat);
        chatMember2.setUser(friend);
        chatMembersRepository.save(chatMember2);

    }
    public void createChatMembers(ChatMember chatMembersRequest){
        chatMembersRepository.save(chatMembersRequest);
    }
}
