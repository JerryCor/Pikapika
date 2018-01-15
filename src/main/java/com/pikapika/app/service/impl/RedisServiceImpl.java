package com.pikapika.app.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.pikapika.app.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {
	
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

	@Override
	public void set(String key, Object value) {
		 ValueOperations<String,Object> vo = redisTemplate.opsForValue();
         vo.set(key, value);
	}

	@Override
	public Object get(String key) {
		ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        return vo.get(key);
	}

	@Override
	public void set(String key, Object value, long time, TimeUnit timeUnit) {
		ValueOperations<String,Object> vo = redisTemplate.opsForValue();
        vo.set(key, value, time, timeUnit);
	}

	@Override
	public void del(String key) {
		redisTemplate.delete(key);
	}

	@Override
	public boolean isExist(String key) {
		if(key == null){
			return false;
		}
		redisTemplate.hasKey(key);
		return false;
	}

}
