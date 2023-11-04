package com.example.bromessenger.service;

import com.example.bromessenger.model.Message;
import com.example.bromessenger.repositories.ChatsRepository;
import com.example.bromessenger.repositories.MessageRepository;
import com.example.bromessenger.repositories.UserRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Data
public class MessageService {
    private final MessageRepository messageRepository;
    private final ChatsRepository chatRepository;
    private final UserRepository userRepository;
    public ResponseEntity<Message> createMessage(Message messagesRequest) {
        Message messages =messageRepository.save(messagesRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(messages);
    }


    public ResponseEntity<?> updateMessage(Long id, Message messageRequest) {
        Message messages = messageRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("нихуя не найдено"));
        messages.setContent(messageRequest.getContent());
        messageRepository.save(messages);
        return ResponseEntity.ok("сообщение обновлено");
    }

    public ResponseEntity<?> deleteMessageById(Long id) {
        Message messages = messageRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("ошибка удаления"));
        messageRepository.delete(messages);

        return ResponseEntity.ok("сообщение удалено");
    }

    public List<Message> getAllMessageService(){
        return messageRepository.findAll();
    }
    public Message getMessageByIdService(Long id){
        return messageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found"));
    }
}
