package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.JoinRequestDto;
import com.ssafy.youandi.dto.LoginRequestDto;
import com.ssafy.youandi.dto.TokenDto;


public interface UserService {
    // 로그인
    public TokenDto login(LoginRequestDto loginRequestDto) throws Exception;

    // 회원가입
    public void join(JoinRequestDto joinRequestDto) throws Exception;

}
