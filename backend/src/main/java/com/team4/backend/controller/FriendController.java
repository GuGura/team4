package com.team4.backend.controller;


import com.team4.backend.model.dto.FriendDTO;
import com.team4.backend.service.FriendService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ControllerProperties.API_VERSION)
//@CrossOrigin(origins = "http://localhost:3000")
public class FriendController {
    private final FriendService friendService;


    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }
    @PostMapping("/findFriend")
    public List<FriendDTO> findFriend() {
        System.out.println("1");
        System.out.println("친구리스트 Controller 접속 체크 ");
         List<FriendDTO> friendList = friendService.findFriend();
         return friendList;

    }
}