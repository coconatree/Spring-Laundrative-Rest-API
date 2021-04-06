package com.laundrative_v2.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

/**
 *
 * Some questions about this file ?
 *
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer
{

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry)
	{
		registry.addEndpoint("/socket").setAllowedOrigins("*").withSockJS();
	}

	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registration)
	{
		registration.setMessageSizeLimit(1024 * 1024);
		registration.setSendBufferSizeLimit(1024 * 1024);
		registration.setSendTimeLimit(20000);
	}

	@Bean
	public ServletServerContainerFactoryBean createServletServerContainerFactoryBean()
	{
		ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
		container.setMaxTextMessageBufferSize(1024 * 1024);
		container.setMaxBinaryMessageBufferSize(1024 * 1024);
		return container;
	}
}