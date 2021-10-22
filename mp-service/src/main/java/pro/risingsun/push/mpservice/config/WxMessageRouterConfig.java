package pro.risingsun.push.mpservice.config;


import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.risingsun.push.mpservice.handler.ScanHandler;
import pro.risingsun.push.mpservice.handler.ScanUnsubscribedHandler;
import pro.risingsun.push.mpservice.handler.SubscribeHandler;
import pro.risingsun.push.mpservice.model.EventType;
import pro.risingsun.push.mpservice.model.MsgType;

/**
 * @Author: TheR1sing3un
 * @Date: 2021/09/25/12:13
 * @Description: 配置接收消息的路由规则
 */

@Configuration
public class WxMessageRouterConfig {

    private String scanUnsubscribedEventKeyPrefix = "qrscene_";

    @Autowired
    private SubscribeHandler subscribeHandler;

    @Autowired
    private ScanHandler scanHandler;

    @Autowired
    private ScanUnsubscribedHandler scanUnsubscribedHandler;

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpConfig wxMpConfig;

    @Bean
    public WxMpMessageRouter router(){
        //获取微信配置信息
        WxMpConfigStorage wxMpConfigStorage = wxMpService.getWxMpConfigStorage();
        //创建WxMessageRouter对象
        WxMpMessageRouter router = new WxMpMessageRouter(wxMpService);
        router.rule().msgType(MsgType.EVENT).async(false).event(EventType.SUBSCRIBE).eventKey(scanUnsubscribedEventKeyPrefix+wxMpConfig.getSceneStr()).handler(scanHandler).end();//配置用户未关注扫码后关注事件
        router.rule().msgType(MsgType.EVENT).async(false).event(EventType.SUBSCRIBE).handler(subscribeHandler).end();//配置用户关注事件
        router.rule().msgType(MsgType.EVENT).async(false).event(EventType.SCAN).handler(scanHandler).end();//配置用户已关注时扫码事件的路由
        return router;
    }
}
