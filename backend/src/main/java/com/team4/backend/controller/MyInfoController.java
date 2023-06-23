package com.team4.backend.controller;

import com.team4.backend.model.ChannelMember;
import com.team4.backend.model.dto.ResultDTO;
import com.team4.backend.model.dto.ResultDtoProperties;
import com.team4.backend.service.ChannelService;
import com.team4.backend.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public ResponseEntity<ResultDTO> getMyServerList(HttpServletRequest request){
        int memberUID =(int) request.getAttribute(ResultDtoProperties.USER_UID);
        System.out.println(memberUID);
        List<ChannelMember> list = channelService.getMyChannels(memberUID);
        resultDTO = ResultDTO.builder()
                .result(list)
                .message("channel_list callback")
                .build();
        return new ResponseEntity<>(resultDTO,HttpStatus.OK);
    }
}
