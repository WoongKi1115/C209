package com.ssafy.youandi.dto.request;


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
