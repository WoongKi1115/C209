package com.ssafy.youandi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KakaoProfileDto {
    KakaoAccount kakao_account;

    @Data
    public class KakaoAccount {
        private String email;
    }
}
