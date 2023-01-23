package com.ssafy.youandi.config;

import com.ssafy.youandi.entity.user.User;
import com.ssafy.youandi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class JwtUserDetailsService implements UserDetailsService {
////    private final UserRepository userRepository;
////    @Override
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//////        log.info("loadUserByUsername");
//////        User user = userRepository.findByNickName(username)
//////                .orElseThrow(()->new UsernameNotFoundException("사용자가 존재하지 않습니다."));
//////
//////        return JwtUserDetails.builder()
//////                .id(user.getUId())
//////                .nickname(user.getNickname())
//////                .password(user.getPassword())
//////                .role(user.getRole())
//////                .build();
////    }
//}
