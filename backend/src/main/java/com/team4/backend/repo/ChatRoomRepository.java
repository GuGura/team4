package com.team4.backend.repo;

import com.team4.backend.mapper.RedisToMariaDBMigrationMapper;
import com.team4.backend.model.ChatRoom;
import com.team4.backend.pubsub.RedisSubscriber;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class ChatRoomRepository {
    // Redis
    private static final String CHAT_ROOMS = "CHAT_ROOM";

    private final RedisTemplate<String, Object> redisTemplate;
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, ChatRoom> opsHashChatRoom;
    // 채팅방의 대화 메시지를 발행하기 위한 redis topic 정보. 서버별로 채팅방에 매치되는 topic정보를 Map에 넣어 roomId로 찾을수 있도록 한다.
    private Map<String, ChannelTopic> topics;
    // MariaDB의 ChatRoom 정보를 가져오기 위한 Service
    private final RedisToMariaDBMigrationMapper redisToMariaDBMigrationMapper;


    @PostConstruct
    private void init() {
        opsHashChatRoom = redisTemplate.opsForHash();
        topics = new HashMap<>();
    }

    public List<ChatRoom> findAllRoom(String channel_id) {
        // MariaDB에서 데이터를 먼저 가져옵니다.
        List<ChatRoom> chatRoomsFromDB = redisToMariaDBMigrationMapper.getAllChatRoomsFromDB(channel_id);
        // Redis에서 데이터를 가져옵니다.
        List<ChatRoom> chatRoomsFromRedis = opsHashChatRoom.values(CHAT_ROOMS)
                .stream()
                .filter(chatRoom -> chatRoom.getChannel_id().equals(channel_id))
                .collect(Collectors.toList());

        // MariaDB에서 가져온 채팅룸의 ID를 추출합니다.
        Set<String> roomIdsFromDB = chatRoomsFromDB.stream()
                .map(ChatRoom::getRoomId)
                .collect(Collectors.toSet());

        // Redis에서 가져온 채팅룸 중, MariaDB에 없는 채팅룸만 선택합니다.
        List<ChatRoom> chatRoomsFromRedisOnly = chatRoomsFromRedis.stream()
                .filter(chatRoom -> !roomIdsFromDB.contains(chatRoom.getRoomId()))
                .collect(Collectors.toList());

        // 두 데이터를 합칩니다.
        List<ChatRoom> allChatRooms = new ArrayList<>(chatRoomsFromDB);
        allChatRooms.addAll(chatRoomsFromRedisOnly);

        return allChatRooms;
    }

    public ChatRoom findRoomById(String id) {
        return opsHashChatRoom.get(CHAT_ROOMS, id);
    }

    /**
     * 채팅방 생성 : 서버간 채팅방 공유를 위해 redis hash에 저장한다.
     */
    public ChatRoom createChatRoom(String name, boolean room_type, String channel_id) {
        ChatRoom chatRoom = ChatRoom.create(name, room_type, channel_id);
        opsHashChatRoom.put(CHAT_ROOMS, chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }

    public List<ChatRoom> getAllChatRooms() {
        return opsHashChatRoom.values(CHAT_ROOMS);
    }

}
