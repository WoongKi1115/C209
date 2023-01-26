package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.JoinDto;
import com.ssafy.youandi.dto.LoginDto;
import com.ssafy.youandi.dto.TokenDto;


public interface UserService {
    // 로그인
    public TokenDto login(LoginDto loginDto) throws Exception;

    // 회원가입
    public void join(JoinDto joinDto) throws Exception;

}
