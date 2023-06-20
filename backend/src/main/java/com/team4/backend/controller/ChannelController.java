package com.team4.backend.controller;

import com.team4.backend.model.dto.ResultDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(ControllerProperties.API_VERSION)
public class ChannelController {

    @PostMapping("/channel/add")
    public ResponseEntity<ResultDTO> addChannel(@RequestBody Map<String,String> params){
        String nickName = params.get("nickName");
        String fileURL = params.get("fileURL");
        String serverName = params.get("serverName");
        System.out.println(nickName);
        System.out.println(fileURL);
        System.out.println(serverName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
