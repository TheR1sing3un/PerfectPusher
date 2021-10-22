package pro.risingsun.push.mpservice.handler;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.risingsun.push.mpservice.builder.OutTextMessageBuilder;
import pro.risingsun.push.mpservice.model.ReplyDTO;

import java.util.Map;

/**
 * @author TheR1sing3un
 * @date 2021/10/18 21:24
 * @description
 */
@Component
@Slf4j
public class SubscribeHandler implements WxMpMessageHandler {

    @Autowired
    OutTextMessageBuilder textMessageBuilder;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMpXmlMessage, Map<String, Object> map, WxMpService wxMpService, WxSessionManager wxSessionManager) throws WxErrorException {
        ReplyDTO replyDTO = new ReplyDTO(wxMpXmlMessage.getFromUser(),wxMpXmlMessage.getToUser(),"关注成功！");
        WxMpXmlOutMessage message = textMessageBuilder.build(replyDTO);
        log.info(""+replyDTO);
        return message;
    }
}
