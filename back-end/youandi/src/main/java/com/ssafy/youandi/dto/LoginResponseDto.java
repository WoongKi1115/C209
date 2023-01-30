package com.ssafy.youandi.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class LoginResponseDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String email;

    @NotNull
    private String accessToken;
    @NotNull
    private String refreshToken;
}