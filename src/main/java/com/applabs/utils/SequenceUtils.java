/**
 * 
 */
package com.applabs.utils;


import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.applabs.config.RedisConfig;

/**
 * @author prabodh.hend
 *
 */
@Component
public class SequenceUtils {

	private Logger logger = LoggerFactory.getLogger(SequenceUtils.class);

	@Autowired
	RedisConfig config;

	private RedisTemplate<String, Object> redisTemplate;

	private final Integer INCREMENTBY = 1;

	@PostConstruct
	public void init() {
		this.redisTemplate = config.redisTemplate();
	}

	public String generateNextSequence(String key) {
		String nextSequence = "";
		redisTemplate.opsForValue().increment(key, this.INCREMENTBY);
		nextSequence = redisTemplate.opsForValue().get(key).toString();
		System.out.println("Key :" + key + " Next Sequence Generated :" + nextSequence);
		logger.debug("Key :" + key + " Next Sequence Generated :" + nextSequence);
		return nextSequence;
	}

	public String getSequenceByKey(String key) {
		String nextSequence = "";
		nextSequence = redisTemplate.opsForValue().get(key).toString();
		System.out.println("Key :" + key + " Next Sequence Generated :" + nextSequence);
		logger.debug("Key :" + key + " Next Sequence Generated :" + nextSequence);
		return nextSequence;
	}

}
