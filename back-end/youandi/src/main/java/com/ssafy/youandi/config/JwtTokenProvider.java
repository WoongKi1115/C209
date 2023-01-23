package com.ssafy.youandi.config;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.security.Key;

//@Slf4j
//@Component
//@RequiredArgsConstructor
public class JwtTokenProvider implements InitializingBean {
//    @Value("${spring.jwt.secretKey}")
    private String secretKey;

    public static final long ACCESS_TOKEN_VALID_TIME = 1000L * 60 * 30;  // 30분

    public static final long REFRESH_TOKEN_VALID_TIME = 1000L * 60 * 60 * 24 * 7;  // 7일

    private Key key;

//    private final UserDetailsService jwtUserDetailsService;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }
//    public String createAccessToken(Authentication )
}