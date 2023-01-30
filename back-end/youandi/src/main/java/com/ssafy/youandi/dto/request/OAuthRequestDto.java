package com.ssafy.youandi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.LinkedMultiValueMap;

@Getter
@AllArgsConstructor
public class OAuthRequestDto {
    private String url;
    private LinkedMultiValueMap<String,String> map;
}
