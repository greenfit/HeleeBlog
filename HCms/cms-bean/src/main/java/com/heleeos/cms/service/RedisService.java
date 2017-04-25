package com.heleeos.cms.service;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class RedisService {
    
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    private Gson gson = new Gson();

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }
    
    /**
     * 从Redis服务器中获取内容.
     * 
     * @param key 获取的Key
     * @param classOfT 获取内容的类型
     */
    public <T> T get(String key, Class<T> classOfT) {
        return redisTemplate.execute(new RedisCallback<T>() {
            @SuppressWarnings("unchecked")
            public T doInRedis(RedisConnection connection) throws DataAccessException {
                if(connection.exists(key.getBytes())) {
                    String value = redisTemplate.getStringSerializer().deserialize(connection.get(key.getBytes()));
                    if(String.class.equals(classOfT)) {
                        return (T) value;
                    } else {
                        return gson.fromJson(value, classOfT); 
                    }
                }
                return null;
            }}
        );
    }
    
    public String get(String key) {
        String str = get(key, String.class);
        if("null".equals(str)) {
            return null;
        }
        return str;
    }
    
    /**
     * 键是否存在.
     * 
     * @param key 键
     */
    public boolean exists(String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(key.getBytes());
            }
        });
    }
    
    /**
     * 向Redis服务器中放入内容.
     * 
     * @param key 键
     * @param value 值
     * @param timeout 失效时间
     * @param unit 时间单位
     */
    public void put(String key, String value, long timeout, TimeUnit unit) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key.getBytes(), value.getBytes());
                return null;
            }
        });
        expire(key, timeout, unit);
    }
    
    /**
     * 向Redis服务器中放入内容,失效时间为1小时.
     * 
     * @param key 键
     * @param value 值
     */
    public void put(String key, String value) {
        put(key, value, 1, TimeUnit.HOURS);
    }
    
    public void put(String key, Object obj, long timeout, TimeUnit unit) {
        put(key, gson.toJson(obj), timeout, unit);
    }
    
    public void put(String key, Object obj) {
        put(key, gson.toJson(obj));
    }
    
    public void expire(String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }
    
    public void expire(String key) {
        expire(key, 1, TimeUnit.HOURS);
    }
    
    public void clear(String key) {
        put(key, "null", 1, TimeUnit.SECONDS);
    }
}
