package com.ssafy.youandi.service.Impl;

import com.google.gson.Gson;
import com.ssafy.youandi.dto.AccessToken;
import com.ssafy.youandi.config.social.OAuthRequestFactory;
import com.ssafy.youandi.dto.KakaoProfileDto;
import com.ssafy.youandi.dto.OAuthRequestDto;
import com.ssafy.youandi.dto.ProfileDto;
import com.ssafy.youandi.service.ProviderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.naming.CommunicationException;
@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {

    private final RestTemplate restTemplate;
    private final Gson gson;
    private final OAuthRequestFactory oAuthRequestFactory;

    public ProfileDto getProfile(String accessToken, String provider) throws CommunicationException {
        log.info("getProfile accessToken={}",accessToken);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        String profileUrl = oAuthRequestFactory.getProfileUrl(provider);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<String> response = restTemplate.postForEntity(profileUrl, request, String.class);

        try {
            if (response.getStatusCode() == HttpStatus.OK) {
                return extractProfile(response, provider);
            }
        } catch (Exception e) {
            throw new CommunicationException();
        }
        throw new CommunicationException();
    }

    private ProfileDto extractProfile(ResponseEntity<String> response, String provider) {
        if (provider.equals("kakao")) {
            KakaoProfileDto kakaoProfile = gson.fromJson(response.getBody(), KakaoProfileDto.class);
            return new ProfileDto(kakaoProfile.getKakao_account().getEmail());
        } else{
            return null;
        }
    }

    public AccessToken getAccessToken(String code, String provider) throws CommunicationException {
        log.info("getAccessToken");
        log.info("code={},provider={}",code,provider);
        HttpHeaders httpHeaders = new HttpHeaders();
        log.info("1");
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        log.info("2");
        OAuthRequestDto oAuthRequest = oAuthRequestFactory.getRequest(code, provider);
        log.info("3");
        HttpEntity<LinkedMultiValueMap<String, String>> request = new HttpEntity<>(oAuthRequest.getMap(), httpHeaders);
        log.info("4");
        log.info("oAuthRequest.getUrl() ={}",oAuthRequest.getUrl());
        ResponseEntity<String> response = restTemplate.postForEntity(oAuthRequest.getUrl(), request, String.class);
        try {
            if (response.getStatusCode() == HttpStatus.OK) {
                return gson.fromJson(response.getBody(), AccessToken.class);
            }
        } catch (Exception e) {
            throw new CommunicationException();
        }
        throw new CommunicationException();
    }
}
