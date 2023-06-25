package com.team4.backend.controller;

import com.team4.backend.model.Channel;
import com.team4.backend.model.dto.MyChannelsDTO;
import com.team4.backend.model.dto.ResultDTO;
import com.team4.backend.model.dto.ResultDtoProperties;
import com.team4.backend.service.ChannelService;
import com.team4.backend.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerProperties.API_VERSION)
public class ChannelController {

    private ResultDTO resultDTO;
    private final ChannelService channelService;
    @PostMapping("/channel/add")
    public ResponseEntity<ResultDTO> addChannel(@RequestBody Map<String, String> params, HttpServletRequest request) {
        int memberUID =(int) request.getAttribute(ResultDtoProperties.USER_UID);
        String inviteCode = params.get("inviteCode");
        List<MyChannelsDTO> myChannelDTO = new ArrayList<>();
        if (inviteCode.trim().length() == 0){
            String fileURL = params.get("fileURL");
            String serverName = params.get("serverName");

            myChannelDTO.add(channelService.createChannel(memberUID,fileURL,serverName));
        }


        resultDTO = ResultDTO.builder()
                .result(myChannelDTO)
                .status(true)
                .build();
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }
}
