package com.ssafy.youandi.service.Impl;

import com.ssafy.youandi.entity.user.SignupRequest;
import com.ssafy.youandi.entity.user.User;
import com.ssafy.youandi.repository.UserRepository;
import com.ssafy.youandi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public Long singUp(SignupRequest signupRequest) throws Exception {
        if(userRepository.findByEmail(signupRequest.getEmail()).isPresent()){
            throw new Exception("이미 존재하는 이메일입니다.");
        }
        if(!signupRequest.getPassword().equals(signupRequest.getCheckedpassword())){
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        User user = userRepository.save(signupRequest.toEntity());
        user.encodePassword(passwordEncoder);

        return user.getUserId();
    }
}
