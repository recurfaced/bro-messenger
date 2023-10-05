package com.example.bromessenger.controller;

import com.example.bromessenger.model.ChatMembers;
import com.example.bromessenger.model.Chats;
import com.example.bromessenger.repositories.ChatMembersRepository;
import com.example.bromessenger.repositories.ChatsRepository;
import com.example.bromessenger.service.ChatService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@RequestMapping("/api/chat")
public class ChatController {
    private final ChatMembersRepository chatMembersRepository;
    private final ChatsRepository chatsRepository;
    private final ChatService chatService;

    @PostMapping("/create")
    public ResponseEntity<Chats> createChat(@RequestBody Chats chatsRequest){
     Chats chats = chatsRepository.save(chatsRequest);
     return ResponseEntity.status(HttpStatus.CREATED).body(chats);
    }
    @PostMapping("/add/members")
    public ResponseEntity<ChatMembers> createChatMembers(
            @RequestBody ChatMembers chatMembersRequest){
        ChatMembers chatMembers =chatMembersRepository.save(chatMembersRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(chatMembers);
    }

    @DeleteMapping("/delete/chatmember")
    public ResponseEntity<?> deleteMember(@RequestParam Long userId,@RequestParam Long chatId){
        try {
            chatService.deleteMemberFromChat(userId,chatId);
            return ResponseEntity.ok("пользователь удален с чата");
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove chat member.");
        }
    }
}
