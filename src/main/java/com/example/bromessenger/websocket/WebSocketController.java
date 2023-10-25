package com.example.bromessenger.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Slf4j
public class WebSocketController {

    @MessageMapping("/ws")
    @SendTo("/topic/messages")
    public MessageWs sendMessage(MessageWs message) {
        log.info("WebSocketController");
        return message;
    }
}