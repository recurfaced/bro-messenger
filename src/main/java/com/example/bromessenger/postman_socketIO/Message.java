package com.example.bromessenger.postman_socketIO;

import lombok.Data;

@Data
public class Message {
    private String senderName;
    private String targetUserName;
    private String message;
}
