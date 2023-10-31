package com.example.bromessenger.controller;

import com.example.bromessenger.model.ChatMember;
import com.example.bromessenger.model.request.chat.CreateChatRequest;
import com.example.bromessenger.service.ChatService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createChat(@RequestBody CreateChatRequest chatsRequest) {
        chatService.createChat(chatsRequest);
    }

    @PostMapping("/add/members")
    public void createChatMembers(
            @RequestBody ChatMember chatMembersRequest) {
        chatService.createChatMembers(chatMembersRequest);
    }

    @DeleteMapping("/member")
    public void deleteMember(@RequestParam Long userId, @RequestParam Long chatId) {
            chatService.deleteMemberFromChat(userId, chatId);
    }
}
