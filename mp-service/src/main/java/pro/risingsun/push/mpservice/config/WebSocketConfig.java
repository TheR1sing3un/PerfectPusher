package pro.risingsun.push.mpservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author TheR1sing3un
 * @date 2021/10/19 11:32
 * @description
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //addEndpoint表示添加了一个/socket端点，客户端就可以通过这个端点来进行连接。
        //withSockJS()的作用是开启SockJS支持(指定使用SockJS协议)
        registry.addEndpoint("/socket/login").setAllowedOrigins("*").withSockJS();
    }

}
