package com.example.bromessenger.websocket;

import lombok.Data;

@Data
public class MessageWs {
    private String content;
    private String sender;
}
