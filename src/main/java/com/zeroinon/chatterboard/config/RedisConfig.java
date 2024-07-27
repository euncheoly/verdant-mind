package com.zeroinon.chatterboard.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

@Component
@EnableMBeanExport(registration= RegistrationPolicy.IGNORE_EXISTING)
public class RedisConfig {

    @Bean
    public JedisPool createJedisPool() {
        return new JedisPool("localhost", 6379);

    }




}
