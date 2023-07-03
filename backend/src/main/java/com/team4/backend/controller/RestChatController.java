package com.team4.backend.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.team4.backend.mapper.RedisToMariaDBMigrationMapper;
import com.team4.backend.model.ChatRoom;
import com.team4.backend.repo.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(ControllerProperties.API_VERSION+"/chat")
public class RestChatController {

    private final RedisToMariaDBMigrationMapper redisToMariaDBMigrationMapper;
    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/rooms/{channel_id}")
    public ResponseEntity<?> room(@PathVariable("channel_id") String channel_id) {
        List<ChatRoom> list = chatRoomRepository.findAllRoom(channel_id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/room")
    public ResponseEntity<?> createRoom(@RequestBody Map<String,?> params) {
        String name = (String)params.get("name");
        boolean room_type = (boolean)params.get("room_type");
        String channel_id = (String) params.get("channel_id");
        System.out.println("RestChatController chatnnel_Id= "+channel_id);
        ChatRoom chatRoom = chatRoomRepository.createChatRoom(name, room_type, channel_id);
        return new ResponseEntity<>(chatRoom,HttpStatus.CREATED);
    }
}
