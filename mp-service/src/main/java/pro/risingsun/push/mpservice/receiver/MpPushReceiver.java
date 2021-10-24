package pro.risingsun.push.mpservice.receiver;

import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.risingsun.push.model.PushDTO;
import pro.risingsun.push.mpservice.service.PushMpService;

/**
 * @author TheR1sing3un
 * @date 2021/10/24 18:49
 * @description
 */
@Component
public class MpPushReceiver {

    @Autowired
    PushMpService pushMpService;

    @RabbitListener(queues = "MpQueue")
    public void receive(PushDTO pushDTO) throws WxErrorException {
        pushMpService.pushMpMessage(pushDTO);
    }

}
