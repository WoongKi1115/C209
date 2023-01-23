package com.ssafy.youandi.entity.user;

import com.ssafy.youandi.entity.Role;
import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private int uId;

    private String nickname;
    private String email;
    private String password;

    @Column(name = "create_date", insertable = false, updatable = false)
    private LocalDateTime createDate;

    private String token;
    @Enumerated(EnumType.STRING)
    private Role role = Role.MEMBER;

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password=passwordEncoder.encode(password);
    }
}