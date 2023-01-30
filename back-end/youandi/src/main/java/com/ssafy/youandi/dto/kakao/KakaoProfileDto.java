package com.ssafy.youandi.dto.kakao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KakaoProfileDto {
    KakaoAccount kakao_account;
//    KakaoProfile profile;

    @Data
    public class KakaoAccount {
        private String email;
        private String nickName;
    }

}
