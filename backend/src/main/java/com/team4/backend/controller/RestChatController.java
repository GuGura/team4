package com.team4.backend.controller;


import com.team4.backend.mapper.RedisToMariaDBMigrationMapper;
import com.team4.backend.model.ChatRoom;
import com.team4.backend.repo.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(ControllerProperties.API_VERSION+"/chat")
public class RestChatController {

    private final RedisToMariaDBMigrationMapper redisToMariaDBMigrationMapper;
    private final ChatRoomRepository chatRoomRepository;
    @GetMapping("/rooms")
    public ResponseEntity<?> room() {
        List<ChatRoom> list = chatRoomRepository.findAllRoom();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
