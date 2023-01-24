package com.ssafy.youandi.entity.user;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {

    private String email;
    private String password;
    private String name;
    
}
