package com.nttdata.gatewayserverproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import io.netty.resolver.DefaultAddressResolverGroup;
import reactor.netty.http.client.HttpClient;

@EnableEurekaClient
@SpringBootApplication
public class GatewayServerprojectApplication {
	
	@Bean
	public HttpClient httpClient() {
		return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayServerprojectApplication.class, args);
	}

}
