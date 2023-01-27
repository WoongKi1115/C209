package com.ssafy.youandi.service.Impl;

import com.ssafy.youandi.config.jwt.JwtTokenProvider;
import com.ssafy.youandi.dto.JoinRequestDto;
import com.ssafy.youandi.dto.LoginRequestDto;
import com.ssafy.youandi.dto.TokenDto;
import com.ssafy.youandi.entity.Role;
import com.ssafy.youandi.entity.user.User;
import com.ssafy.youandi.repository.UserRepository;
import com.ssafy.youandi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public TokenDto login(LoginRequestDto loginRequestDto) throws Exception {
        Optional<User> user = userRepository.findByEmail(loginRequestDto.getEmail());

        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.get().getPassword())){
            throw new Exception("잘못된 비밀번호입니다.");
        }

        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 JwtUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = jwtTokenProvider.generateToken(authentication);

        return tokenDto;
    }

    @Transactional
    @Override
    public void join(JoinRequestDto joinRequestDto) throws Exception {
        if(userRepository.findByEmail(joinRequestDto.getEmail()).isPresent()){
            throw new Exception("이미 존재하는 이메일입니다.");
        }
        if(!joinRequestDto.getPassword().equals(joinRequestDto.getCheckedpassword())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        User user = User.builder()
                .email(joinRequestDto.getEmail())
                .password(passwordEncoder.encode(joinRequestDto.getPassword()))
                .nickname(joinRequestDto.getNickname())
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
