package com.onuraktas.stocktrackingsystem.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisCacheImpl {

    private final RedisTemplate<Object, Object> redisTemplate;

    public RedisCacheImpl(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void deleteCache(String key){
        redisTemplate.delete(key);
    }

    public void deleteAllCache(){
        redisTemplate.delete(redisTemplate.keys("*"));
    }

    public <T> T getCache(String key){
        return (T) redisTemplate.opsForValue().get(key);
    }

    public <T> void setCache(String key, T value){
        redisTemplate.opsForValue().set(key, value);
    }

    public <T> void setCache(String key, T value, Long timeout, TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, timeout, timeUnit);
    }

    public Boolean hasKey(String key) {
    	return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}
