package com.example.bromessenger.websocket;

import com.example.bromessenger.model.Messages;
import com.example.bromessenger.service.MessageService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
@Data
public class WebSocketController {
    private final MessageService messageService;
    @MessageMapping("/ws")
    @SendTo("/topic/messages")
    public MessageWs sendMessage(MessageWs message) {
        messageService.testCreateMessage(message);
        log.info("Received message: {}", message.getContent());
        return message;
    }
}
