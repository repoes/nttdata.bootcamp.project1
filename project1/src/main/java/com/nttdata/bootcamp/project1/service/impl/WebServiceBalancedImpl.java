package com.nttdata.bootcamp.project1.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.bootcamp.project1.service.WebService;
import com.nttdata.bootcamp.project1.util.RestUtil;

import reactor.core.publisher.Mono;

@Service("ClientServiceBalanced")
public class WebServiceBalancedImpl implements WebService {
	
	@Value("${backend.eureka.url}")
	private String url;
	
	@Autowired
	@Qualifier("wcLoadBalanced")
	private WebClient.Builder webClientBuilder;
	
	@Override
	public Mono<String> getMessage() {
		// TODO Auto-generated method stub
		return webClientBuilder
				.clientConnector(RestUtil.getDefaultClientConnector())
				.build()
				.get()
				.uri(url)
				.retrieve()
				.bodyToMono(String.class)
				.map(response -> "Mensaje Del MS = " + response);
	}

}
