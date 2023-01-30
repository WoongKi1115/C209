package com.ssafy.youandi.controller;

import com.ssafy.youandi.service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.CommunicationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/social/login")
public class SocialController {
    @Value("${spring.social.kakao.client_id}")
    private String kakaoClientId;
    @Value("${spring.social.kakao.redirect_uri}")
    private String kakaoRedirect;
    private final Environment env;
    @Autowired
    private UserServiceImpl userServiceimpl;
    // 카카오 로그인 페이지 테스트
    @GetMapping()
    public String socialKakaoLogin() {
        StringBuilder loginUrl1 = new StringBuilder()
                .append(env.getProperty("spring.social.kakao.url.login"))
                .append("?client_id=").append(kakaoClientId)
                .append("&response_type=code")
                .append("&redirect_uri=").append(kakaoRedirect);
        return "redirect:"+loginUrl1.toString();
    }

    @ResponseBody
    @GetMapping("/kakao")
    public void kakaoCallback(@RequestParam String code) {
        System.out.println(code);
    }
    // 인증 완료 후 리다이렉트 페이지
//    @GetMapping(value = "/{provider}")
//    public String redirectKakao(@RequestParam String code, @PathVariable String provider) {
////        mav.addObject("code", code);
////        mav.setViewName("redirect");
//        return "성공";
//    }



}
