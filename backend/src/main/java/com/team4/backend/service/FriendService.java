package com.team4.backend.service;

import com.team4.backend.mapper.FriendMapper;
import com.team4.backend.model.FriendDTO;
import com.team4.backend.model.FriendDTO2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendMapper friendMapper;
    public List<FriendDTO2> findSearchUsers(String username, int memberUID) {
        List<FriendDTO2> list = friendMapper.findSearchUsers(username, memberUID);
        System.out.println(list);
        return list;
    }
}
