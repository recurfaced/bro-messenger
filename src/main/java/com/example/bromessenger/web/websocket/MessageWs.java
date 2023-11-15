package com.example.bromessenger.web.websocket;

import lombok.Data;

@Data
public class MessageWs {
    private String content;
    private String sender;
    private Long userId;
    private Long chatId;
}
