package com.anginfo.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.anginfo.utils.HandshakeInterceptor;
import com.anginfo.utils.WebsocketEndPoint;

@Configuration  
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		 registry.addHandler(new WebsocketEndPoint(), "/websocket").addInterceptors(new HandshakeInterceptor());  
	     registry.addHandler(new WebsocketEndPoint(), "/sockjs/websocket").addInterceptors(new HandshakeInterceptor()).withSockJS();  
	}
	
}
