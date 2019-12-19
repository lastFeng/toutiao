package com.example.toutiao.controller.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author guowf
 * @mail: guowf_buaa@163.com
 * @date created in 2019/12/19 22:55
 * @description:
 * 配置WebSocket的服务端点
 */
@ServerEndpoint("/ws/asset")
@Component
public class WebSocketServer {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    /**
     * 用来记录当前连接数，应该设计成线程安全的
     */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    /**
     * 并发包的线程安全set，用来存储每个客户端对应的MyWebSocket对象
     */
    private static CopyOnWriteArraySet<Session> sessionSet = new CopyOnWriteArraySet<>();

    /**
     * webSocket建立连接时，调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        sessionSet.add(session);
        int cnt = onlineCount.incrementAndGet();
        logger.info("有连接加入，当前在线人数为：{}", cnt);

        sendMessage(session, "连接成功");
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose(Session session) {
        sessionSet.remove(session);
        int cnt = onlineCount.decrementAndGet();
        logger.info("有一连接关闭！ 当前在线人数为：{}" , cnt);
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("收到来自客户端的消息：" + message);

        sendMessage(session, "收到消息，消息内容：" + message);
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        logger.error("发生错误: {}, SessionId: {}", error.getMessage(), session.getId());
    }

    /**
     * 实现服务器主动推送，推送内容和位置可以进行设置
     * @param message
     * @throws IOException
     */
    public static void sendMessage(Session session, String message){
        try {
            session.getBasicRemote().sendText(String.format("%s (From Server, SessionId=%s)", message, session.getId()));
        } catch (IOException e) {
            logger.error("发送消息出错: {}", e.getMessage());
        }
    }

    /**
     * 群发消息
     * @param message
     * @throws IOException
     */
   public static void BroadCastInfo(String message) throws IOException {
       for (Session session : sessionSet) {
           if (session.isOpen()) {
               sendMessage(session, message);
           }
       }
   }

    /**
     * 指定session发送消息
     * @param message
     * @param sessionId
     * @throws IOException
     */
   public static void SendIdInfo(String message, String sessionId) throws IOException {
        Session session = null;
       for (Session s : sessionSet) {
           if (s.getId().equals(sessionId)) {
               session = s;
               break;
           }
       }

       if (session != null) {
           sendMessage(session, message);
       } else {
           logger.warn("没有找到你指定ID的会话：{}" , sessionId);
       }
   }

//    /**
//     * 对在线次数进行线程安全操作
//     * */
//    public static synchronized int getOnlineCount() {
//        return onlineCount;
//    }
//
//    public static synchronized void addOnLineCount() {
//        WebSocketServer.onlineCount++;
//    }
//
//    public static synchronized void subOnlineCount() {
//        WebSocketServer.onlineCount--;
//    }
}
