package com.team4.backend.controller;

import com.team4.backend.model.FriendDTO;
import com.team4.backend.model.dto.ResultDtoProperties;
import com.team4.backend.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(ControllerProperties.API_VERSION)
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;
    @GetMapping("/friend/search/{username}")
    public ResponseEntity<?> getSearchList(@PathVariable("username") String username, HttpServletRequest request){
        int memberUID = (int) request.getAttribute(ResultDtoProperties.USER_UID);
        List<FriendDTO> list = friendService.findSearchUsers(username,memberUID);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

}