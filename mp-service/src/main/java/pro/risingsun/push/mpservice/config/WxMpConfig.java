package pro.risingsun.push.mpservice.config;

import lombok.Data;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author TheR1sing3un
 * @date 2021/10/18 20:06
 * @description
 */
@ConfigurationProperties(prefix = "wx.mp")
@Configuration
@Data
public class WxMpConfig {
    @Value("${wx.mp.appId}")
    private String appId;
    @Value("${wx.mp.appSecret}")
    private String appSecret;
    @Value("${wx.mp.token}")
    private String token;
    @Value("${wx.mp.sceneStr}")
    private String sceneStr;
    @Value("${wx.mp.expire}")
    private Integer expire;
    @Value("${wx.mp.showCodeUrl}")
    private String showCodeUrl;
    @Bean
    public WxMpService wxMpService(){
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId(appId);
        config.setSecret(appSecret);
        config.setToken(token);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
        return wxMpService;
    }
}
