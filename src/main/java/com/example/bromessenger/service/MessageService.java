package com.example.bromessenger.service;

import com.example.bromessenger.model.Chats;
import com.example.bromessenger.model.Messages;
import com.example.bromessenger.model.Users;
import com.example.bromessenger.repositories.ChatsRepository;
import com.example.bromessenger.repositories.MessageRepository;
import com.example.bromessenger.repositories.UserRepository;
import com.example.bromessenger.websocket.MessageWs;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@Data
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatsRepository chatRepository;
    private final UserRepository userRepository;
    public ResponseEntity<Messages> createMessage(Messages messagesRequest) {
        Messages messages =messageRepository.save(messagesRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(messages);
    }

    public ResponseEntity<Messages> testCreateMessage(MessageWs messageRequest) {
        Messages messages = new Messages();
        messages.setContent(messageRequest.getContent());
        messages.setCreatedAt(LocalDateTime.now());

        Chats chat = chatRepository.findById(messageRequest.getChatId())
                .orElseGet(() -> new Chats(messageRequest.getChatId()));
        Users user = userRepository.findById(messageRequest.getUserId())
                .orElseGet(() -> new Users(messageRequest.getUserId()));

        messages.setChat(chat);
        messages.setUser(user);

        Messages savedMessage = messageRepository.save(messages);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessage);
    }

    public ResponseEntity<?> updateMessage(Long id, Messages messageRequest) {
        Messages messages = messageRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("нихуя не найдено"));
        messages.setContent(messageRequest.getContent());
        messageRepository.save(messages);
        return ResponseEntity.ok("сообщение обновлено");
    }

    public ResponseEntity<?> deleteMessageById(Long id) {
        Messages messages = messageRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ошибка удаления"));
        messageRepository.delete(messages);

        return ResponseEntity.ok("сообщение удалено");
    }
}
