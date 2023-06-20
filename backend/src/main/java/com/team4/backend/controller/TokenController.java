package com.team4.backend.controller;

import com.team4.backend.model.dto.ResultDTO;
import com.team4.backend.model.dto.ResultDtoProperties;
import com.team4.backend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TokenController {

    ResultDTO resultDTO;

    private final JwtService jwtService;

    @PostMapping("/check-token")
    public ResponseEntity<?> checkToken(HttpServletRequest request) {
        boolean status = (boolean) request.getAttribute(ResultDtoProperties.STATUS);
        String message = (String) request.getAttribute(ResultDtoProperties.MESSAGE);
        if (!status)
            return new ResponseEntity<>(jwtService.getResult(false, message), HttpStatus.OK);
        else
            return new ResponseEntity<>(jwtService.getResult(true, "accessJwt 인증성공"), HttpStatus.OK);
    }

    @PostMapping("/check-refreshToken")
    public ResponseEntity<?> checkRefreshToken(HttpServletRequest request) {
        boolean status = (boolean) request.getAttribute(ResultDtoProperties.STATUS);
        String message = (String) request.getAttribute(ResultDtoProperties.MESSAGE);
        if (!status)
            return new ResponseEntity<>(jwtService.getResult(false, message), HttpStatus.OK);
        else
            return new ResponseEntity<>(jwtService.getResult(true, "토큰 재발급 성공"), HttpStatus.OK);
    }
}
