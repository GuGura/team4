package com.team4.backend.service;

import com.team4.backend.mapper.FriendMapper;
import com.team4.backend.model.FriendDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendMapper friendMapper;
    public List<FriendDTO> findSearchUsers(String username, int memberUID) {
        List<FriendDTO> list = friendMapper.findSearchUsers(username, memberUID);
        System.out.println(list);
        return list;
    }
}
