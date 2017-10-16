package com.albert.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration  
@EnableCaching
@EnableRedisHttpSession
public class RedisConfig{
}