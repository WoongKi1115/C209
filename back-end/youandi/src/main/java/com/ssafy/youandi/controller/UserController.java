package com.ssafy.youandi.controller;

import com.ssafy.youandi.entity.user.SignupRequest;
import com.ssafy.youandi.repository.UserRepository;
import com.ssafy.youandi.service.Impl.UserServiceImpl;
import com.ssafy.youandi.service.JwtService;
import com.ssafy.youandi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private UserRepository userRepository;

    //회원가입
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public Long join(@Valid @RequestBody SignupRequest signupRequest) throws Exception{
        return userService.singUp(signupRequest);
    }




}
