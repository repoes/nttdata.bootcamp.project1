package com.nttdata.bootcamp.bankapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "project1")
public class AppConfig {
//	@Value("${spring.data.mongodb.host}")
//	private String host;
//	
//	@Value("${spring.data.mongodb.port}")
//	private String port;
//	
//	@Value("${spring.data.mongodb.database}")
//	private String database;
}
