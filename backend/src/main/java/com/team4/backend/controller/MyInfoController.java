package com.team4.backend.controller;

import com.team4.backend.model.Member;
import com.team4.backend.model.dto.MemberTestDTO;
import com.team4.backend.model.dto.MyChannelsDTO;
import com.team4.backend.model.dto.ResultDTO;
import com.team4.backend.model.dto.ResultDtoProperties;
import com.team4.backend.service.ChannelService;
import com.team4.backend.service.MemberService;
import com.team4.backend.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerProperties.API_VERSION)
public class MyInfoController {
    ResultDTO resultDTO;
    private final ChannelService channelService;
    private final MemberService memberService;
    @PutMapping("/myInfo/init")
    public ResponseEntity<ResultDTO> initMyInfo() {
        String email = UserUtil.getEmail();
        resultDTO = ResultDTO.builder()
                .result(email)
                .build();
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }

    @GetMapping("/myInfo/channelList")
    public ResponseEntity<ResultDTO> getMyServerList(HttpServletRequest request){
        int memberUID =(int) request.getAttribute(ResultDtoProperties.USER_UID);
        List<MyChannelsDTO> list = channelService.getMyChannels(memberUID);
        resultDTO = ResultDTO.builder()
                .result(list)
                .message("channel_list callback")
                .build();
        return new ResponseEntity<>(resultDTO,HttpStatus.OK);
    }

    @GetMapping("/myInfo/lobby")
    public  ResponseEntity<ResultDTO> getLobbyInfo(HttpServletRequest request){
        int memberUID =(int) request.getAttribute(ResultDtoProperties.USER_UID);
        Member member = memberService.getLobbyInfoByMemberUID(memberUID);
        resultDTO = ResultDTO.builder()
                .result(UserUtil.memberToReturn(member))
                .message("lobby Info callback")
                .build();
        return new ResponseEntity<>(resultDTO,HttpStatus.OK);
    }

}
