package com.team4.backend.controller;

import com.team4.backend.mapper.RedisToMariaDBMigrationMapper;
import com.team4.backend.model.ChatMessage;
import com.team4.backend.model.ChatRoom;
import com.team4.backend.repo.ChatMessageRepository;
import com.team4.backend.repo.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController // @Controller 대신 @RestController를 사용하여, JSON 형태로 응답할 수 있게 만듭니다.
@RequestMapping("/api/v1/chat")
public class ChatRoomController {

    private final RedisToMariaDBMigrationMapper redisToMariaDBMigrationMapper;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @GetMapping("/room")
    public String rooms(Model model) {
        return "api/v1/chat/room";
    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room(@RequestParam("channel_id") String channel_id) {
        return chatRoomRepository.findAllRoom(channel_id);
    }

    @GetMapping("/room/enter/{roomId}")
    @ResponseBody
    public List<ChatMessage> roomDetail(Model model, @PathVariable String roomId) {
        List<ChatMessage> chatMessages = redisToMariaDBMigrationMapper.getChatMessagesFromDB(roomId);
        chatMessages.addAll(chatMessageRepository.getChatMessagesByRoomId(roomId));
        model.addAttribute("roomId", roomId);
        model.addAttribute("chatMessages", chatMessages);
        return chatMessages;
    }


    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }
}
