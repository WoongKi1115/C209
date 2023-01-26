package com.ssafy.youandi.dto;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationDto {

    private String email;
    private String password;
    private String name;
    
}
