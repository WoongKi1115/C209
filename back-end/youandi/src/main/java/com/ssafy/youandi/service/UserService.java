package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.request.*;
import com.ssafy.youandi.dto.response.JoinResponseDto;
import com.ssafy.youandi.dto.response.LoginResponseDto;
import com.ssafy.youandi.dto.response.TokenResponseDto;
import org.springframework.http.ResponseEntity;


public interface UserService {
    // 로그인
    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws Exception;

    // 회원가입
    public JoinResponseDto join(JoinRequestDto joinRequestDto) throws Exception;
    public TokenResponseDto reIssue(ReIssueRequestDto reIssueRequestDto) throws Exception;

    public LoginResponseDto loginUserByProvider(String code,String provider) throws Exception;
    public ResponseEntity<?> update(UpdateRequestDto updateRequestDto) throws Exception;
    public ResponseEntity<?> logout(LogoutRequestDto logoutRequestDto) throws Exception;
}
