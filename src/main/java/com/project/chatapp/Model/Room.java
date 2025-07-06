package com.project.chatapp.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Room")
public class Room {
    @Id
    private Long id ;
    private List<ChatMessage> Messages =new ArrayList<>();
}
