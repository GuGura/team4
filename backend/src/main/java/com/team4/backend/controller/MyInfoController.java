package com.team4.backend.controller;

import com.team4.backend.model.dto.ResultDTO;
import com.team4.backend.util.UserUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ControllerProperties.API_VERSION)
public class MyInfoController {

    @PutMapping("/myInfo/init")
    public ResponseEntity<ResultDTO> initMyInfo() {
        String email = UserUtil.getEmail();

        ResultDTO resultDTO = ResultDTO.builder()
                .result(email)
                .build();
        return new ResponseEntity<>(resultDTO, HttpStatus.OK);
    }
}
