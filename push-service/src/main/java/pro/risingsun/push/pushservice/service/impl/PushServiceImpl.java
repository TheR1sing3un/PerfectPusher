package pro.risingsun.push.pushservice.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.risingsun.push.model.PushDTO;
import pro.risingsun.push.model.UserChannelDTO;
import pro.risingsun.push.pushservice.service.PushService;
import pro.risingsun.push.pushservice.service.UserFeignService;

/**
 * @author TheR1sing3un
 * @date 2021/10/24 12:30
 * @description
 */
@Service
public class PushServiceImpl implements PushService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    UserFeignService userFeignService;

    @Value("${mq.mail.exchange-name}")
    private String MAIL_EXCHANGE_NAME;

    @Value("${mq.mail.routing-key}")
    private String MAIL_ROUTING_KEY;

    @Value("${mq.mp.exchange-name}")
    private String MP_EXCHANGE_NAME;

    @Value("${mq.mp.routing-key}")
    private String MP_ROUTING_KEY;

    @Override
    public void pushWithSendKey(String sendKey, PushDTO pushDTO) {
        //根据sendKey获取用户信息
        UserChannelDTO userInfo = userFeignService.getUserChannelBySendKey(sendKey);
        System.out.println(userInfo);
        //若开启邮件推送
        if (userInfo.getMailStatus()){
            pushDTO.setToUser(userInfo.getMailConfig().getMailAddress());
            sendEmail(pushDTO);
        }
        //若开启官方公众号推送
        if (userInfo.getWxMpStatus()){
            pushDTO.setToUser(userInfo.getOpenId());
            sendMpMessage(pushDTO);
        }
    }

    @Override
    public void sendEmail(PushDTO pushDTO) {
        rabbitTemplate.convertAndSend(MAIL_EXCHANGE_NAME,MAIL_ROUTING_KEY,pushDTO);
    }

    @Override
    public void sendMpMessage(PushDTO pushDTO) {
        rabbitTemplate.convertAndSend(MP_EXCHANGE_NAME,MP_ROUTING_KEY,pushDTO);
    }

}
