package com.ssafy.youandi.service.Impl;


import com.ssafy.youandi.config.jwt.JwtTokenProvider;
import com.ssafy.youandi.dto.*;
import com.ssafy.youandi.dto.kakao.AccessToken;
import com.ssafy.youandi.dto.kakao.ProfileDto;
import com.ssafy.youandi.dto.request.*;
import com.ssafy.youandi.dto.response.JoinResponseDto;
import com.ssafy.youandi.dto.response.LoginResponseDto;
import com.ssafy.youandi.dto.response.Response;
import com.ssafy.youandi.dto.response.TokenResponseDto;
import com.ssafy.youandi.entity.Role;
import com.ssafy.youandi.entity.redis.RedisKey;
import com.ssafy.youandi.entity.user.User;
import com.ssafy.youandi.exception.InvalidRefreshTokenException;
import com.ssafy.youandi.exception.UserNotFoundException;
import com.ssafy.youandi.repository.UserRepository;
import com.ssafy.youandi.service.RedisService;
import com.ssafy.youandi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.naming.CommunicationException;
import java.util.Optional;
import java.util.UUID;

import static com.ssafy.youandi.entity.redis.RedisKey.REGISTER;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RedisService redisService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProviderServiceImpl providerServiceImpl;
    private final Response response;

    // 회원가입
    @Transactional
    @Override
    public JoinResponseDto join(JoinRequestDto joinRequestDto) throws Exception {
        if(userRepository.findByEmail(joinRequestDto.getEmail()).isPresent()){
            throw new Exception("이미 존재하는 이메일입니다.");
        }
        if(userRepository.findByNickname(joinRequestDto.getNickname()).isPresent()){
            throw new Exception("이미 존재하는 닉네임입니다.");
        }
        if(!joinRequestDto.getPassword().equals(joinRequestDto.getCheckedpassword())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        String authToken = UUID.randomUUID().toString();
        redisService.setDataWithExpiration(RedisKey.EAUTH.getKey()+joinRequestDto.getEmail()
                , authToken,60*5L);

        User user = User.builder()
                .email(joinRequestDto.getEmail())
                .password(passwordEncoder.encode(joinRequestDto.getPassword()))
                .nickname(joinRequestDto.getNickname())
                .provider(null)
                .role(Role.ROLE_USER).
                build();

        userRepository.save(user);
        return JoinResponseDto.builder()
                .email(user.getEmail())
                .build();
    }

    // 로컬 로그인 구현
    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws Exception {
        User user = userRepository
                .findByEmail(loginRequestDto.getEmail()).orElseThrow(UserNotFoundException::new);

        if(!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw new Exception("잘못된 비밀번호입니다.");
        }


        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 JwtUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        String refreshToken = jwtTokenProvider.createRefreshToken();
        redisService.setDataWithExpiration(RedisKey.REGISTER.getKey() +user.getEmail(), refreshToken, JwtTokenProvider.REFRESH_TOKEN_VALID_TIME);

        return new LoginResponseDto(user.getNickname(), user.getEmail(),jwtTokenProvider.createAccessToken(authentication),refreshToken);
    }

    // 소셜 로그인 구현
    @Transactional
    public LoginResponseDto loginUserByProvider(String code,String provider) throws CommunicationException {
        log.info("loginUserByProvider");
        AccessToken accessToken = providerServiceImpl.getAccessToken(code,provider);
        ProfileDto profileDto = providerServiceImpl.getProfile(accessToken.getAccess_token(),provider);
        log.info("profileDto : " + profileDto.toString());
        Optional<User> findUser = userRepository.findByEmailAndProvider(profileDto.getEmail(),provider);

        if(findUser.isPresent()){
            User user = findUser.get();
            user.updateRefreshToken(jwtTokenProvider.createRefreshToken());
            return new LoginResponseDto(user.getNickname(),user.getEmail(),jwtTokenProvider.createToken(user.getEmail()), user.getRefreshToken());
        }else{
            User saveUser = saveUser(profileDto,provider);
            saveUser.updateRefreshToken(jwtTokenProvider.createRefreshToken());

            return new LoginResponseDto(saveUser.getNickname(),saveUser.getEmail(),jwtTokenProvider.createToken(saveUser.getEmail())
                    ,saveUser.getRefreshToken());
        }
    }

    private User saveUser(ProfileDto profileDto, String provider) {
        User user = User.builder()
                .nickname(profileDto.getNickName())
                .email(profileDto.getEmail())
                .password(null)
                .role(Role.ROLE_USER)
                .provider(provider)
                .build();
        User saveUser = userRepository.save(user);
        return saveUser;
    }

    // refreshtoken 재발행
    @Transactional
        public TokenResponseDto reIssue(ReIssueRequestDto reIssueRequestDto){
        String findRefreshToken = redisService.getData(REGISTER.getKey() + reIssueRequestDto.getEmail());
        if(findRefreshToken == null || !findRefreshToken.equals(reIssueRequestDto.getRefreshToken())){
            log.info("refreshToken이 일치하지 않습니다.");
            throw new InvalidRefreshTokenException();
        }

        User user = userRepository.findByEmail(reIssueRequestDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String accessToken = jwtTokenProvider.createAccessToken(authentication);
        String refreshToken = jwtTokenProvider.createRefreshToken();

        user.updateRefreshToken(refreshToken);


        return new TokenResponseDto(accessToken,refreshToken);
    }

    public User findMemberByToken(TokenDto requestDto) {
        Authentication auth = jwtTokenProvider.getAuthentication(requestDto.getAccessToken());
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();
        return userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
    }
    @Transactional
    @Override
    public ResponseEntity<?> update(UpdateRequestDto updateRequestDto) {
        if (!updateRequestDto.getPassword().equals(updateRequestDto.getCheckedpassword())) {
            return response.fail("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }
        Optional<User> updateUser = userRepository.findByEmail(updateRequestDto.getEmail());

        updateUser.ifPresent(selectUser->{
            selectUser.setNickname(updateRequestDto.getNickname());
            selectUser.setPassword(passwordEncoder.encode(updateRequestDto.getPassword()));

            userRepository.save(selectUser);
        });

        return response.success("회원정보 수정에 성공했습니다.");
    }

    // 로컬 로그아웃
    @Override
    public ResponseEntity<?> logout(LogoutRequestDto logoutRequestDto) {
        // 1. Access Token 검증
        if (!jwtTokenProvider.validateToken(logoutRequestDto.getAccessToken())) {
            return response.fail("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
        }
        // 2. Access Token 에서 User email 을 가져옵니다.
        Authentication authentication = jwtTokenProvider.getAuthentication(logoutRequestDto.getAccessToken());

        redisService.deleteData(RedisKey.REGISTER.getKey() +authentication.getName());
        return response.success("로그아웃 되었습니다.");
    }



}
