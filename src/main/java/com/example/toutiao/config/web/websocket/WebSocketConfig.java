package com.example.toutiao.config.web.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/19 22:53
 * @description:
 * WebSocket的配置文件
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new TextWebSocketHandler(), "/ws/asset")
                .addInterceptors(new HttpSessionHandshakeInterceptor())
                .setAllowedOrigins("*");
    }

    /**
     * 启用Websocket的支持
     * @return
     */
    //@Bean
    //public ServerEndpointExporter serverEndpointExporter() {
    //    return new ServerEndpointExporter();
    //}


}
