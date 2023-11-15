package com.example.bromessenger.web.request.chat;

import com.example.bromessenger.model.enums.TypeChat;
import lombok.Data;

@Data
public class CreateChatRequest {
    private String name;
    private TypeChat typeChat;
    private Long userId;
    private Long friendId;
}
