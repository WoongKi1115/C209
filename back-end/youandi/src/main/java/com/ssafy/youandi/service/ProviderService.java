package com.ssafy.youandi.service;

import com.ssafy.youandi.dto.AccessToken;
import com.ssafy.youandi.dto.ProfileDto;
import org.springframework.http.*;

import javax.naming.CommunicationException;

public interface ProviderService {
    public AccessToken getAccessToken(String code, String provider) throws CommunicationException ;
    public ProfileDto getProfile(String accessToken, String provider) throws CommunicationException ;
//    private ProfileDto extractProfile(ResponseEntity<String> response,String provider);

}
