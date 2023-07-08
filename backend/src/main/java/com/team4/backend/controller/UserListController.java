package com.team4.backend.controller;

import com.team4.backend.model.dto.UserListDto;
import com.team4.backend.service.UserListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat/room/list")
public class UserListController {

    private final UserListService userListService;

    @GetMapping("/UserList")
    public List<UserListDto> onlineUsers() {
        return userListService.getUserList();
    }
}
