package com.applabs.config;


import org.assertj.core.util.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClientURI;

/**
 * @author prabodh.hend
 *
 */
@Configuration
@ComponentScan(basePackages = "com.applabs")
public class MongoConfig {

	@Autowired
	private Environment env;

	
	@Bean
	public MongoDbFactory mongoDbFactory() throws Exception {
		
		MongoClientURI mongoClientURI = new MongoClientURI(Preconditions.checkNotNull(env.getProperty("mongo.url")));
		
		return new MultiTenantMongoDbFactory(mongoClientURI);
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}

}