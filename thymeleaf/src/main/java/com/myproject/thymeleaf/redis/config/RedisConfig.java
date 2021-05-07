package com.myproject.thymeleaf.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author zhanjianjian
 * @since 2021/5/7
 */
@Configuration
@EnableCaching // 开启缓存支持
public class RedisConfig extends CachingConfigurerSupport {

    /**
     * @return 自定义策略生成的key
     * @description 自定义的缓存key的生成策略 若想使用这个key
     * 只需要讲注解上keyGenerator的值设置为keyGenerator即可</br>
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuffer sb = new StringBuffer();
            sb.append(target.getClass().getName());
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    @Value("${spring.redis.database.shiro}")
    int shiroDB;

    /**
     * Shiro Session RedisTemplate 配置
     */
    @Bean("shiroRedisTemplate")
    public RedisTemplate<String, Object> shiroRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisStandaloneConfiguration configuration = lettuceConnectionFactory.getStandaloneConfiguration();
        RedisStandaloneConfiguration shiroConfiguration = new RedisStandaloneConfiguration();

        shiroConfiguration.setDatabase(shiroDB);
        shiroConfiguration.setHostName(configuration.getHostName());
        shiroConfiguration.setPort(configuration.getPort());
        shiroConfiguration.setPassword(configuration.getPassword());

        LettuceConnectionFactory shiroLettuceConnectionFactory = new LettuceConnectionFactory(shiroConfiguration,
                lettuceConnectionFactory.getClientConfiguration());
        shiroLettuceConnectionFactory.setDatabase(shiroDB);
        shiroLettuceConnectionFactory.afterPropertiesSet();
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(shiroLettuceConnectionFactory);
        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);// key序列化
        return redisTemplate;
    }

    /**
     * 通用 RedisTemplate 配置
     */
    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        // 默认设置为主库
        lettuceConnectionFactory.setDatabase(0);
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        RedisSerializer<?> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);// key序列化
        return redisTemplate;
    }
}
