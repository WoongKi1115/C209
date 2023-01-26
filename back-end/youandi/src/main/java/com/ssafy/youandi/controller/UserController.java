package com.ssafy.youandi.controller;

import com.ssafy.youandi.config.jwt.JwtFilter;
import com.ssafy.youandi.dto.JoinDto;
import com.ssafy.youandi.dto.LoginDto;
import com.ssafy.youandi.dto.TokenDto;
import com.ssafy.youandi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) throws Exception {
        TokenDto tokenDto = userService.login(loginDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + tokenDto.getAccessToken());

        return new ResponseEntity<>(tokenDto, headers, HttpStatus.OK);
    }

    // 회원가입
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> join(@Valid @RequestBody JoinDto joinDto) throws Exception{
        userService.join(joinDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
