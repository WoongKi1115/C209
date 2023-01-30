package com.ssafy.youandi.dto;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ReIssueDto {

    private String email;
    private String refreshToken;
}
