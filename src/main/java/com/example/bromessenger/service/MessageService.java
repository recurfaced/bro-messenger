package com.example.bromessenger.service;

import com.example.bromessenger.model.Messages;
import com.example.bromessenger.repositories.MessageRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Data
public class MessageService {
    private final MessageRepository messageRepository;
    public ResponseEntity<Messages> createMessage(Messages messagesRequest) {
        Messages messages =messageRepository.save(messagesRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(messages);
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
