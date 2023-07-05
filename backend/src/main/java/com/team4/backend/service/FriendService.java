package com.team4.backend.service;


import com.team4.backend.mapper.FriendMapper;
import com.team4.backend.model.dto.FriendDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    private final FriendMapper friendMapper;

    public FriendService(FriendMapper friendMapper) {
        this.friendMapper = friendMapper;
    }
    public List<FriendDTO> findFriend() {
        System.out.println("친구 리스트 service단 접속 체크 ");
        return  friendMapper.findAll();

    }
}
