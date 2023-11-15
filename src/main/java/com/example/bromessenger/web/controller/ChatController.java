package com.example.bromessenger.web.controller;

import com.example.bromessenger.model.ChatMember;
import com.example.bromessenger.service.ChatService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
final class ChatController {
    private final ChatService chatService;

    //TODO
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createChat(HttpServletRequest request, @PathVariable Long id) {
       return chatService.createChat(request,id);
    }

    //TODO
    @PostMapping("/add/members")
    public void createChatMembers(
            @RequestBody ChatMember chatMembersRequest) {
        chatService.createChatMembers(chatMembersRequest);
    }

    @DeleteMapping("/member")
    public void deleteMember(@RequestParam Long userId, @RequestParam Long chatId) {
            chatService.deleteMemberFromChat(userId, chatId);
    }
    private ChatController(ChatService chatService){
        this.chatService = chatService;
    }
}
