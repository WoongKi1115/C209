package com.ssafy.youandi.config.social;

import com.ssafy.youandi.dto.request.OAuthRequestDto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
@Slf4j
@Component
@RequiredArgsConstructor
public class OAuthRequestFactory {
    private final KakaoInfo kakaoInfo;

    public  OAuthRequestDto getRequest(String code, String provider){
        LinkedMultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        if(provider.equals("kakao")){
            map.add("grant_type","authorization_code");
            map.add("client_id", kakaoInfo.getKakaoClientId());
            map.add("redirect_uri", kakaoInfo.getKakaoRedirect());
            map.add("code", code);
            System.out.println("getRequest :" + map.toString());

            return new OAuthRequestDto(kakaoInfo.getKakaoTokenUrl(),map);
        }
        else {
            new Exception("잘못된 접근입니다.");
            log.info("잘못된 접근입니다.");
            return null;
        }
    }
    public  String getProfileUrl(String provider){
        if(provider.equals("kakao")){
            return kakaoInfo.getKakaoProfileUrl();
        }else {
            log.info("잘못된 접근입니다.");
            return "잘못된 접근입니다.";
        }
    }
    @Getter
    @Component
    static class KakaoInfo {

        @Value("${spring.social.kakao.client_id}")
        String kakaoClientId;
        @Value("${spring.social.kakao.redirect_uri}")
        String kakaoRedirect;
        @Value("${spring.social.kakao.url.token}")
        private String kakaoTokenUrl;
        @Value("${spring.social.kakao.url.profile}")
        private String kakaoProfileUrl;

    }

}
