package com.point.springbootinit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置
 * 主要是配置Redis的序列化规则，用Jackson2JsonRedisSerializer替换默认的jdkSerializer
 */
@Configuration
public class RedisConfig {
    /**
     * retemplate相关配置
     *
     * @param redisConnectionFactory
     * @return
     */
    @Primary
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(redisConnectionFactory);

        // String 的 序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key 采用 String的序列化
        template.setKeySerializer(stringRedisSerializer);
        // hash 的key采用String的序列化
        template.setHashKeySerializer(stringRedisSerializer);

        template.afterPropertiesSet();

        return template;
    }
}
