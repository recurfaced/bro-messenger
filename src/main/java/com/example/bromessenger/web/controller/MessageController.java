package com.example.bromessenger.web.controller;

import com.example.bromessenger.model.Message;
import com.example.bromessenger.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
final class MessageController {
    private final MessageService messageService;

    @GetMapping("/")
    public List<Message> getAllMessages() {
        return messageService.getAllMessageService();
    }

    //TODO
    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable Long id) {
        return messageService.getMessageByIdService(id);
    }

    //TODO
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Message> createMessage(@RequestBody Message messagesRequest) {
        return messageService.createMessage(messagesRequest);
    }
    //TODO
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMessage(@PathVariable Long id, @RequestBody Message messageRequest) {
        return messageService.updateMessage(id, messageRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        return messageService.deleteMessageById(id);
    }


}
