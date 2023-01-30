package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.*;
import com.ssafy.youandi.entity.user.User;


public interface UserService {
    // 로그인
    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws Exception;

    // 회원가입
    public JoinResponseDto join(JoinRequestDto joinRequestDto) throws Exception;
    public TokenResponseDto reIssue(ReIssueDto reIssueDto) throws Exception;

    public LoginResponseDto loginUserByProvider(String code,String provider) throws Exception;
}
