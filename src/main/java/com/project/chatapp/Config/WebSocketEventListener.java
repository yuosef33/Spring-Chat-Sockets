package com.project.chatapp.Config;

import com.project.chatapp.Model.ChatMessage;
import com.project.chatapp.Model.ChatType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messageSendingOperations;

    public WebSocketEventListener(SimpMessageSendingOperations messageSendingOperations) {
        this.messageSendingOperations = messageSendingOperations;
    }


    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event)
    {
        StompHeaderAccessor headerAccessor=StompHeaderAccessor.wrap(event.getMessage());
        String username =(String) headerAccessor.getSessionAttributes().get("username");
        if(username !=null){
            log.info("user disconnected:{}",username);
           ChatMessage message=new ChatMessage(null,username, ChatType.LEAVE);
           messageSendingOperations.convertAndSend("/topic/all",message);
        }
    }



}
