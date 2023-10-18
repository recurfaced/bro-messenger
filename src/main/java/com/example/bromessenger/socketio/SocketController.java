package com.example.bromessenger.socketio;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/socket")
@Data
public class SocketController {
    private final SocketIOServer socketIOServer;

    @PostMapping("/send-message")
    public void sendMessage(String message) {
        socketIOServer.getBroadcastOperations().sendEvent("message", message);
    }
}
