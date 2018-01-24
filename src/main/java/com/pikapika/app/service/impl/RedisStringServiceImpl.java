package com.pikapika.app.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.pikapika.app.service.StringRedisService;

@Service
public class RedisStringServiceImpl implements StringRedisService {
	
    @Resource
    private StringRedisTemplate stringRedisTemplate;

	@Override
	public void set(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	@Override
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	@Override
	public void set(String key, String value, long time, TimeUnit timeUnit) {
		stringRedisTemplate.opsForValue().set(key, value, time, timeUnit);
	}

	@Override
	public void del(String key) {
		stringRedisTemplate.delete(key);
	}

	@Override
	public boolean isExist(String key) {
		if(key == null){
			return false;
		}
		stringRedisTemplate.hasKey(key);
		return false;
	}

}
