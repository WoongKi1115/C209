package com.ssafy.youandi.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ReIssueRequestDto {

    private String email;
    private String refreshToken;
}
