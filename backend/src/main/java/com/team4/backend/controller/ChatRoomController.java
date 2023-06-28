package com.team4.backend.controller;

import com.team4.backend.mapper.RedisToMariaDBMigrationMapper;
import com.team4.backend.model.ChatMessage;
import com.team4.backend.model.ChatRoom;
import com.team4.backend.repo.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/chat")
public class ChatRoomController {

    @Autowired
    private RedisToMariaDBMigrationMapper redisToMariaDBMigrationMapper;
    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/room")
    public String rooms(Model model) {
        return "/api/v1/chat/room";
    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        return chatRoomRepository.findAllRoom();
    }

    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name, boolean room_type) {
        return chatRoomRepository.createChatRoom(name, room_type);
    }

    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        List<ChatMessage> chatMessages = redisToMariaDBMigrationMapper.getChatMessagesFromDB(roomId);
        model.addAttribute("roomId", roomId);
        model.addAttribute("chatMessages", chatMessages);
        return "/api/v1/chat/roomdetail";
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }
}
