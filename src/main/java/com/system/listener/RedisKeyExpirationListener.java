package com.system.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @Author： XO
 * @Description：
 * @Date： 2020/5/6 16:12
 */

@Slf4j
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {


    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();

        String bodyStr = new String(body);

        log.info("redis 监听失效："+bodyStr);
    }



}
