package com.team4.backend.controller;

import com.team4.backend.mapper.RedisToMariaDBMigrationMapper;
import com.team4.backend.model.ChatMessage;
import com.team4.backend.repo.ChatMessageRepository;
import com.team4.backend.repo.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ChatMessageRepository chatMessageRepository;
    private final RedisToMariaDBMigrationMapper redisToMariaDBMigrationMapper;
    private final ChannelTopic channelTopic;



    /**
     * websocket "/pub/chat/message"로 들어오는 메시징을 처리한다.
     */
    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        chatMessageRepository.save(message);
        System.out.println("roomID: "+ message.getRoomId());
        // Websocket에 발행된 메시지를 redis로 발행한다(publish)
        redisTemplate.convertAndSend(channelTopic.getTopic(), message);
    }



    @ResponseBody
    @GetMapping("/enter/{roomId}")
    public List<ChatMessage> getChatMessages(@PathVariable String roomId) {
        return redisToMariaDBMigrationMapper.getChatMessagesFromDB(roomId);
    }
}
