package pro.risingsun.push.mpservice.builder;

import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.springframework.stereotype.Component;
import pro.risingsun.push.mpservice.model.ReplyDTO;


/**
 * @Author: TheR1sing3un
 * @Date: 2021/09/25/14:06
 * @Description: 对返回消息的封装(配置FromUser,ToUser和返回msgType)
 */
@Component
public class OutTextMessageBuilder implements OutMessageBuilder {

    @Override
    public WxMpXmlOutMessage build(ReplyDTO replyDTO) {
        WxMpXmlOutTextMessage wxMpXmlOutTextMessage = WxMpXmlOutMessage.TEXT().content(replyDTO.getContent()).toUser(replyDTO.getToUsername()).fromUser(replyDTO.getFromUsername()).build();
        return wxMpXmlOutTextMessage;
    }

}
