package com.applabs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author prabodh.hend
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.applabs" })
public class HitCount {

	private static final Logger logger = LoggerFactory.getLogger(HitCount.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(HitCount.class);
		System.out.println("Main - 8081");
		logger.info("Main - 8081");
	}
}
