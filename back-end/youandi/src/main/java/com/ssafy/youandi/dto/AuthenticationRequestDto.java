package com.ssafy.youandi.dto;


import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequestDto {

    private String email;
    private String password;
    private String name;
    
}
