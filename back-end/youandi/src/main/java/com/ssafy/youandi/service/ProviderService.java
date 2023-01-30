package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.kakao.AccessToken;
import com.ssafy.youandi.dto.kakao.ProfileDto;

import javax.naming.CommunicationException;

public interface ProviderService {
    public AccessToken getAccessToken(String code, String provider) throws CommunicationException ;
    public ProfileDto getProfile(String accessToken, String provider) throws CommunicationException ;
//    private ProfileDto extractProfile(ResponseEntity<String> response,String provider);

}
