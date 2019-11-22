package com.mywebcrawler.webcrawlerservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Ravindra Bakkamanthala
 * 
 * This is a Redis Configuration class.
 * The host and port are configured in application.yml file.
 *
 */
@Configuration
public class RedisConfiguration {
	
	@Value("${redis.host}")
	public String redisHost;

	@Value("${redis.port}")
	public int redisport;
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, redisport);
	    return new JedisConnectionFactory(config);
	}
	 
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}
	
}
