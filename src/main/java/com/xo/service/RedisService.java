package com.xo.service;

public interface RedisService {

    int DEFAULT_DB = 0x0;

    //
    int TOKEN_DB = 0x1;
    // 过期监听
    int LISTENER_DB = 0x2;
    // 防止重复请求
    int REPETITION_DB = 0x3;
    // 短信
    int SMS_DB = 0x4;
    // 商品
    int PRODUCT_DB = 0x5;

    void set(String key, Object value);

    void set(String key, Object value, int seconds, int db);

    String get(String key, int db);

    void del(String key, int db);

    void flush(int db);

    String randomKey(int db);

    //判断指定的key是否存在
    boolean exists(String key);

    //判断指定的key是否存在
    boolean exists(String key, int db);

    //自增操作
    Long increment(String key, long delta ,int db);
}