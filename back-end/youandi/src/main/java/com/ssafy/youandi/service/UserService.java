package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.SignupRequest;


public interface UserService {
    //회원가입
    public Long singUp(SignupRequest signupRequest) throws Exception;

}
