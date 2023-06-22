package com.team4.backend.controller;

import com.team4.backend.model.dto.ResultDTO;
import com.team4.backend.service.ChannelService;
import com.team4.backend.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerProperties.API_VERSION)
public class MyInfoController {
    ResultDTO resultDTO;
    private final ChannelService channelService;
    @PutMapping("/myInfo/init")
    public ResponseEntity<ResultDTO> initMyInfo() {
        String email = UserUtil.getEmail();
        System.out.println(email);
        resultDTO = ResultDTO.builder()
                .result(email)
                .build();
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }

    @GetMapping("/myInfo/channelList")
    public ResponseEntity<ResultDTO> getMyServerList(){
        String email = UserUtil.getEmail();
        System.out.println(email);

        resultDTO = ResultDTO.builder()
                .result(email)
                .build();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
