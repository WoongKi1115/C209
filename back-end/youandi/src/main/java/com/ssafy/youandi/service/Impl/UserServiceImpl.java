package com.ssafy.youandi.service.Impl;

import com.ssafy.youandi.config.jwt.JwtTokenProvider;
import com.ssafy.youandi.dto.JoinDto;
import com.ssafy.youandi.dto.LoginDto;
import com.ssafy.youandi.dto.ReIssueDto;
import com.ssafy.youandi.dto.TokenDto;
import com.ssafy.youandi.entity.Role;
import com.ssafy.youandi.entity.user.User;
import com.ssafy.youandi.exception.InvalidRefreshTokenException;
import com.ssafy.youandi.exception.UserNotFoundException;
import com.ssafy.youandi.repository.UserRepository;
import com.ssafy.youandi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ssafy.youandi.entity.redis.RedisKey.REFRESH_TOKEN;
@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisServiceImpl redisServiceImpl;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public TokenDto login(LoginDto loginDto) throws Exception {
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());

        if(passwordEncoder.matches(loginDto.getPassword(), user.get().getPassword())){
            // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
            // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());

            // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
            // authenticate 매서드가 실행될 때 JwtUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            TokenDto tokenDto = jwtTokenProvider.generateToken(authentication);

            return tokenDto;
        } else {
            throw new Exception("잘못된 비밀번호입니다.");
        }
    }

    @Transactional
    @Override
    public void join(JoinDto joinDto) throws Exception {
        if(userRepository.findByEmail(joinDto.getEmail()).isPresent()){
            throw new Exception("이미 존재하는 이메일입니다.");
        }
        if(!joinDto.getPassword().equals(joinDto.getCheckedpassword())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        User user = User.builder()
                .email(joinDto.getEmail())
                .password(passwordEncoder.encode(joinDto.getPassword()))
                .nickname(joinDto.getNickname())
                .role(Role.ROLE_USER).
                build();

        userRepository.save(user);
    }

    //    public TokenDto reIssue(ReIssueDto reIssueDto){
//        String findRefreshToken = redisServiceImpl.getData(REFRESH_TOKEN.getKey() + reIssueDto.getNickname());
//        if(findRefreshToken == null || !findRefreshToken.equals(reIssueDto.getRefreshToken())){
//            log.info("refreshToken이 일치하지 않습니다.");
//            throw new InvalidRefreshTokenException();
//        }
//
//        User user = userRepository.findByEmail(reIssueDto.getNickname())
//                .orElseThrow(() -> new UserNotFoundException());
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String accessToken = jwtTokenProvider.createAccessToken(authentication);
//        String refreshToken = jwtTokenProvider.createRefreshToken();
//        redisServiceImpl.setDataWithExpiration(REFRESH_TOKEN.getKey() + user.getNickname(), refreshToken, JwtTokenProvider.REFRESH_TOKEN_VALID_TIME);
//
//        return TokenDto.builder()
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .build();
//    }
}
