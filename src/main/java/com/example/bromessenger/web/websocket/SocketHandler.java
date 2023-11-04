package com.example.bromessenger.web.websocket;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    @SneakyThrows
    public void handleTextMessage(WebSocketSession session, TextMessage message) {

        String payload = message.getPayload();
        System.out.println("Received message: " + payload);

        for(WebSocketSession webSocketSession : sessions) {
            Map value = new Gson().fromJson(message.getPayload(), Map.class);
            webSocketSession.sendMessage(new TextMessage( value.get("name") + " !"));
        }
    }
    @Override
    @SneakyThrows
    public void afterConnectionEstablished(WebSocketSession session){
        System.out.println("сессия добавлена");
        sessions.add(session);
    }
}