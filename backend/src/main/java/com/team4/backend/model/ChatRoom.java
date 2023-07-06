package com.team4.backend.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoom implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;

    private String roomId;
    private String name;
    private boolean roomType;
    private String channel_id;

    public static ChatRoom create(String name, boolean roomType, String channel_id) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        chatRoom.roomType = roomType;
        chatRoom.channel_id = channel_id;
        return chatRoom;
    }
}

