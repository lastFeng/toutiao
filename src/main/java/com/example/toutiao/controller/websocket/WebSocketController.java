package com.example.toutiao.controller.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/19 23:19
 * @description:
 */
@RestController
@RequestMapping("/api/ws")
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @GetMapping("/sendAll")
    public String sendAllMessage(@RequestParam String message) {
        try {
            WebSocketServer.BroadCastInfo(message);
        } catch (IOException e) {
            logger.error("Controller群发消息出错：{}", e.getMessage());
            return "error";
        }
        return "ok";
    }

    @GetMapping("/sendOne")
    public String sendOneMessage(@RequestParam String message, @RequestParam String id) {
        try {
            WebSocketServer.SendIdInfo(message, id);
        } catch (Exception e) {
            logger.error("Controller发送指定id-{}消息失败：{}", id, message);
            return "error";
        }
        return "ok";
    }
}
