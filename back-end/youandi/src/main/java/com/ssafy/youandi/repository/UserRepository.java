package com.ssafy.youandi.repository;

import com.ssafy.youandi.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    //이메일이 일치하는 User 찾아준다.
    Optional<User> findByEmail(String email);
    Optional<User> findByNickname(String Name);
}
