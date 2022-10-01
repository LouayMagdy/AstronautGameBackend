package com.example.astronautgamebackend.Controller.GameController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@EnableWebSocket
public class WSConfigurer implements WebSocketConfigurer{
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(new MatchController(), "/AstronautGame/Match")
//                .addInterceptors(new HttpSessionHandshakeInterceptor());
    }
    @Bean
    public MatchController echoWebSocket() {
        return new MatchController();
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
