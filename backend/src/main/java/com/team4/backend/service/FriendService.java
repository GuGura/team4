package com.team4.backend.service;

import com.team4.backend.mapper.FriendMapper;
import com.team4.backend.model.FriendDTO;
import com.team4.backend.model.FriendDTO2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<?> save(int memberUID, int sendMemberUID) {
        if (friendMapper.findData(memberUID,sendMemberUID)==1)
            return new ResponseEntity<>("이미 친구추가 신청한 상대입니다.", HttpStatus.PAYMENT_REQUIRED);
        friendMapper.saveRequest(memberUID,sendMemberUID);
        if (friendMapper.findData(memberUID,sendMemberUID)!=1)
            return new ResponseEntity<>("친구신청 실패", HttpStatus.PAYMENT_REQUIRED);
        friendMapper.saveResponse(sendMemberUID,memberUID);
        return new ResponseEntity<>("친구신청 완료",HttpStatus.CREATED);
    }
}
