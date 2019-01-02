package com.caox.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author : nazi
 * @version : 1.0
 * @date : 2018/12/28 10:14
 */
@Repository
public abstract class BaseRedisDao<K,V> {

    @Autowired(required=true)
    protected RedisTemplate<K, V> redisTemplate;
}
