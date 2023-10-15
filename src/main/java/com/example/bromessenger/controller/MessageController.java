package com.example.bromessenger.controller;


import com.example.bromessenger.model.Messages;
import com.example.bromessenger.repositories.MessageRepository;
import com.example.bromessenger.service.MessageService;
import lombok.Data;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@Data
public class MessageController {
    private final MessageRepository messageRepository;
    private final MessageService messageService;

    @GetMapping("/")
    public List<Messages> getAllMessages(){
        return messageRepository.findAll();
    }

    @GetMapping("/{id}")
    public Messages getMessageById(@PathVariable Long id){
        return messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found"));
    }
    @PostMapping("/create")
    public ResponseEntity<Messages> createMessage(@RequestBody Messages messagesRequest){
        return messageService.createMessage(messagesRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMessage(@PathVariable Long id,@RequestBody Messages messageRequest){
        return messageService.updateMessage(id,messageRequest);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id){
        return messageService.deleteMessageById(id);
    }

    @GetMapping("/user")
    public ModelAndView getUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signin");
        return modelAndView;
    }

}
