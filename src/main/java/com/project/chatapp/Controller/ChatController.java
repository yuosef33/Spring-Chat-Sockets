package com.project.chatapp.Controller;

import com.project.chatapp.Dao.RoomDao;
import com.project.chatapp.Model.ChatMessage;
import com.project.chatapp.Model.MessageRequest;
import com.project.chatapp.Model.Room;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@CrossOrigin("*")
public class ChatController {
    private final RoomDao roomDao;

    public ChatController(RoomDao roomDao) {
        this.roomDao = roomDao;
    }
    @MessageMapping("/chat.login")
    @SendTo("/topic/all")
    public ChatMessage login(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username",chatMessage.getSender());
        return chatMessage;

    }
    @MessageMapping("/sendMessage/{roomId}")
    @SendTo("topic/room/{roomId}")
    public ChatMessage sendMessage (@DestinationVariable Long roomId
    , @Payload MessageRequest request
                                    ){
        Optional<Room> roomOptional = roomDao.findById(roomId);
        if(roomOptional.isEmpty()){
throw new RuntimeException("room not found eror 69");
        }
        ChatMessage chatMessage=new ChatMessage();
        chatMessage.setMessage(request.getContent());
        chatMessage.setSender(request.getSender());
        chatMessage.setTime(LocalDateTime.now());
      roomOptional.get().getMessages().add(chatMessage);
      roomDao.save(roomOptional.get());
      return chatMessage;
    }

}
