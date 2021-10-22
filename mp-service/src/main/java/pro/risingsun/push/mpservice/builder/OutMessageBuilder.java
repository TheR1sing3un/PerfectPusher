package pro.risingsun.push.mpservice.builder;


import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import pro.risingsun.push.mpservice.model.ReplyDTO;

/**
 * @Author: TheR1sing3un
 * @Date: 2021/09/25/14:14
 * @Description: 返回消息的构建接口
 */
public interface OutMessageBuilder {

    WxMpXmlOutMessage build(ReplyDTO replyDTO);

}
