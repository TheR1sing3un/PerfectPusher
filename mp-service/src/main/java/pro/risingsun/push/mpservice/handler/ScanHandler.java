package pro.risingsun.push.mpservice.handler;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.risingsun.push.model.CommonResult;
import pro.risingsun.push.model.LoginReplyDTO;
import pro.risingsun.push.mpservice.builder.OutTextMessageBuilder;
import pro.risingsun.push.mpservice.controller.WebSocketServer;
import pro.risingsun.push.mpservice.exception.SocketException;
import pro.risingsun.push.mpservice.model.ReplyDTO;
import pro.risingsun.push.mpservice.service.LoginService;
import pro.risingsun.push.mpservice.service.UserFeignService;

import java.util.Map;

/**
 * @author TheR1sing3un
 * @date 2021/10/18 22:23
 * @description
 */
@Component
@Slf4j
public class ScanHandler implements WxMpMessageHandler {

    @Autowired
    OutTextMessageBuilder builder;

    @Autowired
    LoginService loginService;

    @Autowired
    WebSocketServer webSocketServer;

    @Autowired
    WxMpService wxMpService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
        String openId = wxMpXmlMessage.getFromUser();
        //获取登录返回消息
        LoginReplyDTO loginReplyDTO = loginService.login(openId);
        //发送数据给前端
        ReplyDTO replyDTO = new ReplyDTO(openId,wxMpXmlMessage.getToUser(),null);
        try{
            webSocketServer.sendInfo(CommonResult.ok().data(loginReplyDTO),wxMpXmlMessage.getTicket());
            replyDTO.setContent("登录成功!");
        }catch (SocketException socketException){
            //当发送消息失败时
            replyDTO.setContent("登录失败,请稍后再试");
        }
        WxMpXmlOutMessage message = builder.build(replyDTO);
        log.info(""+replyDTO);
        return message;
    }
}
