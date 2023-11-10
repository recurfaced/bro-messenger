package com.example.bromessenger.web.websocket;

import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

public class HttpSessionHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    @SneakyThrows
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) {

        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpServletRequest httpServletRequest = servletRequest.getServletRequest();

            // Получите токен из атрибутов сессии WebSocket и установите его в атрибуты сессии Spring
            String token = httpServletRequest.getParameter("token");
            System.out.println(token);
            attributes.put("token", token);
        }

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {
        // After handshake
    }
}