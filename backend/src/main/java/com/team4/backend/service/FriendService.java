package com.team4.backend.service;

import com.team4.backend.mapper.FriendMapper;
import com.team4.backend.model.FriendDTO;
import com.team4.backend.model.FriendDTO2;
import com.team4.backend.model.ResultFriend;
import com.team4.backend.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendMapper friendMapper;
    public List<ResultFriend> findSearchUsers(String username, int memberUID) {
        List<FriendDTO2> list = friendMapper.findSearchUsers(username, memberUID);
        List<ResultFriend> listToReturn = new ArrayList<>();
        for (FriendDTO2 friend: list) {
            listToReturn.add(friendToReturn(friend));
        }
        return listToReturn;
    }

    public ResultFriend friendToReturn(FriendDTO2 friend){
        ResultFriend Rfriend = new ResultFriend();
        Rfriend.setID(friend.getID());
        Rfriend.setUSER_ICON_URL(UserUtil.pathToBytes(friend.getUSER_ICON_URL()));
        Rfriend.setUSERNAME(friend.getUSERNAME());
        return Rfriend;
    }

    public ResultFriend friendToReturn(FriendDTO friend){
        ResultFriend Rfriend = new ResultFriend();
        Rfriend.setID(friend.getFRIEND_RECEIVER());
        Rfriend.setUSER_ICON_URL(UserUtil.pathToBytes(friend.getUSER_ICON_URL()));
        Rfriend.setUSERNAME(friend.getUSERNAME());
        return Rfriend;
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
