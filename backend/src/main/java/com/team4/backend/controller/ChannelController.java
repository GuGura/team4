package com.team4.backend.controller;

import com.team4.backend.model.dto.ResultDTO;
import com.team4.backend.util.UserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping(ControllerProperties.API_VERSION)
public class ChannelController {

    @PostMapping("/channel/add")
    public ResponseEntity<ResultDTO> addChannel(@RequestBody Map<String,String> params, HttpServletRequest request){
        boolean status = UserUtil.jwtStatus(request);
        ResultDTO resultDTO;
        if (status){
            String nickName = params.get("nickName");
            String fileURL = params.get("fileURL");
            String serverName = params.get("serverName");
            System.out.println(nickName);
            System.out.println(fileURL);
            System.out.println(serverName);
            resultDTO = ResultDTO.builder()
                    .status(true)
                    .message("채널생성")
                    .build();
            System.out.println("1");
            return new ResponseEntity<>(resultDTO,HttpStatus.OK);
        }else{
            System.out.println("2");
            resultDTO = ResultDTO.builder()
                    .status(false)
                    .message("false")
                    .build();
            return new ResponseEntity<>(resultDTO,HttpStatus.UNAUTHORIZED);
        }
    }
}
