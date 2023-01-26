package com.ssafy.youandi.controller;

import com.ssafy.youandi.config.jwt.JwtTokenProvider;
import com.ssafy.youandi.dto.SignupRequest;
import com.ssafy.youandi.entity.user.User;
import com.ssafy.youandi.repository.UserRepository;
import com.ssafy.youandi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    public static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private UserService userService;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;

    //회원가입
    @PostMapping("/join")
    @ResponseStatus(HttpStatus.OK)
    public Long join(@Valid @RequestBody SignupRequest signupRequest) throws Exception{
        return userService.singUp(signupRequest);
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
