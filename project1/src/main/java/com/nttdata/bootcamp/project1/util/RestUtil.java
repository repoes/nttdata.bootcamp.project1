package com.nttdata.bootcamp.project1.util;

import java.util.concurrent.TimeUnit;

import org.springframework.http.client.reactive.ReactorClientHttpConnector;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.netty.resolver.DefaultAddressResolverGroup;
import reactor.netty.http.client.HttpClient;

public class RestUtil {
	public static ReactorClientHttpConnector getDefaultClientConnector() {
		return getClientConnector(5000,5000,5000);
	}
	
	public static ReactorClientHttpConnector getClientConnector(int connectionTimeout, int readTimeout,
			int writeTimeout) {

		HttpClient httpClient = HttpClient.create()
				.resolver(DefaultAddressResolverGroup.INSTANCE)
				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectionTimeout)
				.doOnConnected(conn -> conn
						.addHandlerLast(new ReadTimeoutHandler(readTimeout, TimeUnit.MILLISECONDS))
						.addHandlerLast(new WriteTimeoutHandler(writeTimeout, TimeUnit.MILLISECONDS)));

		return new ReactorClientHttpConnector(httpClient);
	}
}
