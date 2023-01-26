package com.ssafy.youandi.service.Impl;

import com.ssafy.youandi.config.jwt.JwtTokenProvider;
import com.ssafy.youandi.dto.JoinDto;
import com.ssafy.youandi.dto.LoginDto;
import com.ssafy.youandi.dto.ReIssueDto;
import com.ssafy.youandi.dto.TokenDto;
import com.ssafy.youandi.entity.user.User;
import com.ssafy.youandi.exception.InvalidRefreshTokenException;
import com.ssafy.youandi.exception.UserNotFoundException;
import com.ssafy.youandi.repository.UserRepository;
import com.ssafy.youandi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.youandi.entity.redis.RedisKey.REFRESH_TOKEN;
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisServiceImpl redisServiceImpl;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public TokenDto login(LoginDto loginDto){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

        Authentication authenticate = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String accessToken = jwtTokenProvider.createAccessToken(authenticate);
        String refreshToken = jwtTokenProvider.createRefreshToken();

        redisServiceImpl.setDataWithExpiration(REFRESH_TOKEN.getKey() + authenticate.getName(), refreshToken, JwtTokenProvider.REFRESH_TOKEN_VALID_TIME);

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public TokenDto reIssue(ReIssueDto reIssueDto){
        String findRefreshToken = redisServiceImpl.getData(REFRESH_TOKEN.getKey() + reIssueDto.getNickname());
        if(findRefreshToken == null || !findRefreshToken.equals(reIssueDto.getRefreshToken())){
            log.info("refreshToken이 일치하지 않습니다.");
            throw new InvalidRefreshTokenException();
        }

        User user = userRepository.findByNickname(reIssueDto.getNickname())
                .orElseThrow(() -> new UserNotFoundException());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String accessToken = jwtTokenProvider.createAccessToken(authentication);
        String refreshToken = jwtTokenProvider.createRefreshToken();
        redisServiceImpl.setDataWithExpiration(REFRESH_TOKEN.getKey() + user.getNickname(), refreshToken, JwtTokenProvider.REFRESH_TOKEN_VALID_TIME);

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Transactional
    @Override
    public Long join(JoinDto joinDto) throws Exception {
        if(userRepository.findByEmail(joinDto.getEmail()).isPresent()){
            throw new Exception("이미 존재하는 이메일입니다.");
        }
        if(!joinDto.getPassword().equals(joinDto.getCheckedpassword())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        User user = userRepository.save(joinDto.toEntity());
        user.encodePassword(passwordEncoder);

        return user.getUserId();
    }
}
