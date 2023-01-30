package com.ssafy.youandi.controller;

import com.ssafy.youandi.config.jwt.JwtFilter;
import com.ssafy.youandi.dto.kakao.AuthCode;
import com.ssafy.youandi.dto.request.*;
import com.ssafy.youandi.dto.response.LoginResponseDto;
import com.ssafy.youandi.dto.response.TokenResponseDto;
import com.ssafy.youandi.service.Impl.UserServiceImpl;
import com.ssafy.youandi.service.RedisService;
import io.swagger.annotations.ApiOperation;
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
    private UserServiceImpl userService;
    private final RedisService redisService;

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    // 회원가입
    @ApiOperation(value = "회원가입", notes = "회원가입을 진행한다.")
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> join(@Valid @RequestBody JoinRequestDto joinRequestDto) throws Exception{
        userService.join(joinRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 로그인
    @ApiOperation(value = "로컬 로그인", notes = "로컬을 통해 로그인을 진행한다.")
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        LoginResponseDto loginResponseDto = userService.login(loginRequestDto);

        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + loginResponseDto.getAccessToken());

        return new ResponseEntity<>(loginResponseDto, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "소셜 로그인", notes = "소셜을 통해 로그인을 진행한다.")
    @PostMapping("/login/{provider}")
    public ResponseEntity<LoginResponseDto> loginByKakao(@RequestBody AuthCode authCode, @PathVariable String provider) throws Exception {
        LoginResponseDto responseDto = userService.loginUserByProvider(authCode.getCode(), provider);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 토큰 재발행
    @ApiOperation(value = "토큰 재발급", notes = "Refresh Token을 통해 토큰을 재발급받는다.")
    @PostMapping("/reissue")
    public ResponseEntity<TokenResponseDto> reIssue(@RequestBody ReIssueRequestDto reIssueRequestDto) throws Exception {
        TokenResponseDto responseDto = userService.reIssue(reIssueRequestDto);
        log.info("responseDto : "+ responseDto);
        return new ResponseEntity<>(responseDto,HttpStatus.OK);
    }
    // 회원 정보 수정
    @PutMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody UpdateRequestDto updateRequestDto) {
        return userService.update(updateRequestDto);
    }

    // 로컬 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Valid @RequestBody LogoutRequestDto logoutRequestDto) {
        return userService.logout(logoutRequestDto);
    }
}
