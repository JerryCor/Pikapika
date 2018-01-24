package com.pikapika.app.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public interface StringRedisService {
	/**
	 * 设值键值对
	 * @param key 键
	 * @param value 值
	 */
    public void set(String key, String value); 
    /**
     * 设置键值对（生存周期）
     * @param key 键
     * @param value 值
     * @param time 时间
     * @param timeUnit 时间单位
     */
    public void set(String key, String value, long time, TimeUnit timeUnit);
    /**
     * 通过key获取值
     * @param key 键
     * @return 值
     */
    public String get(String key);
    /**
     * 删除键值对
     * @param key 键
     */
    public void del(String key);
    /**
     * 是否存在key
     * @param key 键
     * @return true:存在 false:不存在
     */
    public boolean isExist(String key);
}
