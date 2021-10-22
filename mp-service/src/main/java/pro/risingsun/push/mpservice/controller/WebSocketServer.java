package pro.risingsun.push.mpservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import pro.risingsun.push.mpservice.config.ServerEncoder;
import pro.risingsun.push.mpservice.exception.SocketException;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author TheR1sing3un
 * @date 2021/10/19 11:32
 * @description
 */
@Component
@ServerEndpoint(value = "/mp/login/socket/{ticket}",encoders = {ServerEncoder.class}) //监听地址
@Slf4j
public class WebSocketServer {
    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;

    private String ticket="";

    /**
     * 连接建立成功
     * @param session
     * @param ticket
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("ticket") String ticket){
        this.session = session;
        this.ticket=ticket;
        if(webSocketMap.containsKey(ticket)){
            webSocketMap.remove(ticket);
            webSocketMap.put(ticket,this);
            //加入set中
        }else{
            webSocketMap.put(ticket,this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        log.info("登录通道连接:"+ticket+",当前登录通道数为:" + getOnlineCount());

        /*try {
            senData("连接成功");
        } catch (IOException e) {
            log.error("登录通道:"+ticket+",网络异常!!!!!!");
        }*/
    }
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(ticket)){
            webSocketMap.remove(ticket);
            //从set中删除
            subOnlineCount();
        }
        log.info("登录通道关闭:"+ticket+",当前登录通道数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("登录通道消息:"+ticket+",报文:"+message);
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("登录通道错误:"+this.ticket+",原因:"+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void senData(Object data) {
        try {
            this.session.getBasicRemote().sendObject(data);
        } catch (EncodeException e) {
            log.error("登录通道错误:"+this.ticket+",原因:"+e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            log.error("登录通道错误:"+this.ticket+",原因:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 发送自定义消息
     * */
    public static void sendInfo(Object data,@PathParam("ticket") String ticket) {
        log.info("发送消息到:"+ticket+"，报文:"+data.toString());
        if(StringUtils.isNotBlank(ticket)&&webSocketMap.containsKey(ticket)){
            webSocketMap.get(ticket).senData(data);
        }else{
            throw new SocketException(SocketException.SOCKET_CLOSED,"通道关闭");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
