package com.xo.web.service.Impl;

import com.alibaba.fastjson.JSON;
import com.xo.web.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private JedisPool jedisPool;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void set(String key, Object value) {
        set(key, value, 0, DEFAULT_DB);
    }

    @Override
    public void set(String key, Object value, int seconds, int db) {
        Jedis jedis = getJedis(db);
        try {
            String setData;
            if (value instanceof String) {
                setData = (String) value;
            } else {
                setData = JSON.toJSONString(value);
            }

            if (seconds <= 0) {
                jedis.set(key, setData);
            } else {
                jedis.setex(key, seconds, setData);
            }
        } finally {
            close(jedis);
        }
    }

    @Override
    public String get(String key, int db) {
        Jedis jedis = getJedis(db);
        try {
            return jedis.get(key);
        } catch (Exception e) {
            log.error("redis get by key error, key: {}, db: {}, exception: {}", key, db, e.getMessage());
            return null;
        } finally {
            close(jedis);
        }
    }

    @Override
    public void del(String key, int db) {
        Jedis jedis = getJedis(db);
        try {
            jedis.del(key);
        } catch (Exception e) {
            log.error("redis delete by key error, key: {}, db: {}, exception: {}", key, db, e.getMessage());
        } finally {
            close(jedis);
        }
    }

    @Override
    public void flush(int db) {

    }

    @Override
    public String randomKey(int db) {
        return null;
    }

    private void close(Jedis jedis) {
        if (jedis != null) {
            try {
                jedis.close();
            } catch (Exception e) {
                log.error("redis close error: {}", e.getMessage());
            }
        }
    }

    private Jedis getJedis(int db) {
        Jedis jedis = jedisPool.getResource();
        jedis.select(db);

        return jedis;
    }

    @Override
    public boolean exists(String key) {
        return exists(key, DEFAULT_DB);
    }

    @Override
    public boolean exists(String key, int db) {
        if (StringUtils.isEmpty(key)) {
            throw new RuntimeException("缺少键名");
        }
        Jedis jedis = getJedis(db);
        try {
            return jedis.exists(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long increment(String key, long delta, int db) {
        Jedis jedis = getJedis(db);
        return jedis.incrBy(key,delta);
    }
}
