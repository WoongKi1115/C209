package com.ssafy.youandi.controller;

import com.ssafy.youandi.config.jwt.JwtFilter;
import com.ssafy.youandi.dto.JoinDto;
import com.ssafy.youandi.dto.LoginDto;
import com.ssafy.youandi.dto.TokenDto;
import com.ssafy.youandi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private UserService userService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto loginDto) throws Exception {
        TokenDto tokenDto = userService.login(loginDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + tokenDto.getAccessToken());

        return new ResponseEntity<>(tokenDto, headers, HttpStatus.OK);
    }

    //회원가입
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public Long join(@Valid @RequestBody JoinDto joinDto) throws Exception{
        return userService.join(joinDto);
    }

    //로그인
//    @PostMapping
//    public String login(@RequestBody Map<String,String> user) throws Exception{
//        logger.info("user email ={}",user.get("email"));
//        User member = userRepository.findByEmail(user.get("email"))
//                .orElseThrow(()->new IllegalArgumentException("가입되지 않은 e-mail 입니다."));
//        return jwtTokenProvider.createAccessToken();
//    }



}
