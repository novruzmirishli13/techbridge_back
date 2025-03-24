package com.example.HZT.Handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.example.HZT.Entity.PlayerState;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GameWebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        PlayerState playerState = objectMapper.readValue(message.getPayload(), PlayerState.class);
        session.sendMessage(new TextMessage("Updated Position: " + playerState.getX() + ", " + playerState.getY()));
    }
}
