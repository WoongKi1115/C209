package com.ssafy.youandi.config.jwt;

import com.ssafy.youandi.config.jwt.JwtUserDetails;
import com.ssafy.youandi.entity.user.User;
import com.ssafy.youandi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Component("userDetailsService")
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername");
        User user = userRepository.findByNickname(username)
                .orElseThrow(()->new UsernameNotFoundException("사용자가 존재하지 않습니다."));

        return JwtUserDetails.builder()
                .id(user.getUserId())
                .nickname(user.getNickname())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
