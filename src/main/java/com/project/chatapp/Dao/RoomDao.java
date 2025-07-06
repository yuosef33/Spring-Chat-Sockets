package com.project.chatapp.Dao;

import com.project.chatapp.Model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomDao extends MongoRepository<Room,Long> {

}
