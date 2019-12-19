package com.example.toutiao.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/19 22:53
 * @description:
 * WebSocket的配置文件
 */
//@Configuration
public class WebSocketConfig {
    /**
     * 启用Websocket的支持
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
