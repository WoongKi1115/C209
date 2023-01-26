package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.JoinDto;
import com.ssafy.youandi.dto.LoginDto;
import com.ssafy.youandi.dto.ReIssueDto;
import com.ssafy.youandi.dto.TokenDto;


public interface UserService {
    // 로그인
    public TokenDto login(LoginDto loginDto) throws Exception;

    // 회원가입
    public Long join(JoinDto joinDto) throws Exception;

    // 재발행
    public TokenDto reIssue(ReIssueDto reIssueDto) throws Exception;

}
