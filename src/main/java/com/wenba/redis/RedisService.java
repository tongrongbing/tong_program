package com.wenba.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: tongrongbing
 * @date: 2020-07-27 17:46
 **/
@Component
public class RedisService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;




}
