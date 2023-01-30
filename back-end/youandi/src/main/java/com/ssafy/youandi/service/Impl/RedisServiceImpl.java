package com.ssafy.youandi.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Service
@Transactional
@RequiredArgsConstructor
public class RedisServiceImpl implements com.ssafy.youandi.service.RedisService {

    private final RedisTemplate redisTemplate;

    public String getData(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void deleteData(String key){
        redisTemplate.delete(key);
    }

    // 만료시간이 있는 Key-Value 값을 생성하여 저장
    public void setDataWithExpiration(String key, String value, Long time){
        if(this.getData(key) != null){
            this.deleteData(key);
        }

        Duration expireDuration = Duration.ofSeconds(time);
        redisTemplate.opsForValue().set(key, value, expireDuration);
    }
}
