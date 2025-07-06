package com.project.chatapp.Controller;

import com.project.chatapp.Dao.RoomDao;
import com.project.chatapp.Model.ChatMessage;
import com.project.chatapp.Model.Room;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/rooms")
public class RoomController {

    private final RoomDao roomDao;

    public RoomController(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestParam Long roomid){
        roomDao.findById(roomid).ifPresent(room -> {
            throw new RuntimeException("Room already exists");
        });
       Room room1=new Room();
        room1.setId(roomid);
        roomDao.save(room1);
        return ResponseEntity.status(HttpStatus.CREATED).body(room1);
    }


    @GetMapping("/{roomid}")
    public ResponseEntity<?> joinRoom(@PathVariable Long roomid){
        Optional<Room> roomOptional = roomDao.findById(roomid);
        if(roomOptional.isEmpty()){
            return ResponseEntity.badRequest().body("this room not exist");
        }

        return ResponseEntity.ok(roomOptional.get());

    }


    @GetMapping("/{roomid}/messages")
    public ResponseEntity<?> getRoomMessages(@PathVariable Long roomid){
        Optional<Room> roomOptional = roomDao.findById(roomid);
        if(roomOptional.isEmpty()){
            return ResponseEntity.badRequest().body("this room not exist");
        }

        return ResponseEntity.ok(roomOptional.get().getMessages());

    }


    @PostMapping("/addmessagetoroom")
    public ResponseEntity<?> addRoommessage(@RequestParam Long roomid,@RequestParam String message,@RequestParam String sender){
        Optional<Room> roomOptional = roomDao.findById(roomid);
        if(roomOptional.isEmpty()){
            return ResponseEntity.badRequest().body("this room not exist");
        }
        Room room1=roomOptional.get();
        ChatMessage chatMessage=new ChatMessage(message,sender);
        room1.getMessages().add(chatMessage);
        roomDao.save(room1);
        return ResponseEntity.ok(room1);
    }

}
