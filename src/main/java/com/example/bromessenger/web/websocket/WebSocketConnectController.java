package com.example.bromessenger.web.websocket;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketConnectController {

    @GetMapping("/ws-connect")
    @ResponseStatus(HttpStatus.SWITCHING_PROTOCOLS) 
    public void wsConnect() {
        
    }
}
