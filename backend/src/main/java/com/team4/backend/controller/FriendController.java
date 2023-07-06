package com.team4.backend.controller;

import com.team4.backend.model.FriendDTO;
import com.team4.backend.model.FriendDTO2;
import com.team4.backend.model.ResultFriend;
import com.team4.backend.model.dto.ResultDtoProperties;
import com.team4.backend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(ControllerProperties.API_VERSION)
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @GetMapping("/friend/search/{username}")
    public ResponseEntity<?> getSearchList(@PathVariable("username") String username, HttpServletRequest request) {
        int memberUID = (int) request.getAttribute(ResultDtoProperties.USER_UID);
        List<ResultFriend> list = friendService.findSearchUsers(username, memberUID);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/friend/send/{friendUID}")
    public ResponseEntity<?> sendFriendRequest(@PathVariable("friendUID") int sendMemberUID, HttpServletRequest request) {
        int memberUID = (int) request.getAttribute(ResultDtoProperties.USER_UID);
        return friendService.save(memberUID,sendMemberUID);
    }

    @GetMapping("/friend/requestUser")
    public ResponseEntity<?> requestUserList(HttpServletRequest request){
        int memberUID = (int) request.getAttribute(ResultDtoProperties.USER_UID);
       // List<FriendDTO2> list = friendService.findRequest
        return new ResponseEntity<>(HttpStatus.OK);
    }


}