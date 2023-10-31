package com.example.bromessenger.model.request.chat;

import com.example.bromessenger.model.enums.TypeChat;
import lombok.Data;

@Data
public class CreateChatRequest {
    private String name;
    private TypeChat typeChat;
    private Long userId;
    private Long friendId;
}
