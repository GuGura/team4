package com.team4.backend.pubsub;

import com.team4.backend.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RedisPublisher {

    private final RedisTemplate<String, Object> redisTemplate;

    public void publish(ChannelTopic topic, ChatMessage message) {
        System.out.println("topic: "+ topic);
        System.out.println("topic get: "+ topic.getTopic());
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
