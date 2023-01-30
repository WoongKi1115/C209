package com.ssafy.youandi.service;

public interface RedisService {
    public String getData(String key);
    public void deleteData(String key);
    public void setDataWithExpiration(String key, String value, Long time);

}
