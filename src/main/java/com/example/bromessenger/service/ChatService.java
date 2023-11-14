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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public final class ChatService {
    private final ChatMembersRepository chatMembersRepository;
    private final ChatsRepository chatsRepository;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;
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

    public Long createChat(HttpServletRequest request, Long friendId) {
        Long userId = userService.getUserIdByJWT(request);
        log.info(friendId.toString());
        log.info(userId.toString());
        User user = getUserById(userId);
        User friend = getUserById(friendId);

        Long existingChatId = isChatExist(user, friend);
        if (existingChatId != null) {
            return existingChatId;
        } else {
            Chat chat = createPrivateChat("лс");
            createChatMember(chat, user);
            createChatMember(chat, friend);

            return chat.getId();
        }
    }

    private Long isChatExist(User user, User friend) {
        List<Chat> existingChats = chatsRepository.findChatsByMembers(user, friend);

        for (Chat chat : existingChats) {
            if (chat.getChatMembers().stream().anyMatch(member -> member.getUser().equals(friend))) {
                return chat.getId();
            }
        }

        return null;
    }


    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    private Chat createPrivateChat(String name) {
        Chat chat = new Chat();
        chat.setName(name);
        chat.setTypeChat(TypeChat.PRIVATE);
        return chatsRepository.save(chat);
    }

    private void createChatMember(Chat chat, User user) {
        ChatMember chatMember = new ChatMember();
        chatMember.setChat(chat);
        chatMember.setUser(user);
        chatMembersRepository.save(chatMember);
    }

    public void createChatMembers(ChatMember chatMembersRequest){
        chatMembersRepository.save(chatMembersRequest);
    }
}
