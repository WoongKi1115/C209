package com.ssafy.youandi.dto.kakao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AccessToken {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private long refresh_token_expires_in;

}