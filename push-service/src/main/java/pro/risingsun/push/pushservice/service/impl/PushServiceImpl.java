package pro.risingsun.push.pushservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.risingsun.push.model.PushDTO;
import pro.risingsun.push.model.UserChannelDTO;
import pro.risingsun.push.pushservice.config.MqNameConfig;
import pro.risingsun.push.pushservice.service.PushService;
import pro.risingsun.push.pushservice.service.UserFeignService;

/**
 * @author TheR1sing3un
 * @date 2021/10/24 12:30
 * @description
 */
@Service
@Slf4j
public class PushServiceImpl implements PushService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    UserFeignService userFeignService;

    @Autowired
    MqNameConfig nameConfig;

    @Override
    public void pushWithSendKey(String sendKey, PushDTO pushDTO) {
        //根据sendKey获取用户信息
        UserChannelDTO userInfo = userFeignService.getUserChannelBySendKey(sendKey);
        log.info("userInfo : {}",userInfo);
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
        //若开启企业微信推送
        if (userInfo.getWxCpStatus()){
            pushDTO.setToUser(userInfo.getCpConfig().getTarget());
            pushDTO.setCorpId(userInfo.getCpConfig().getCorpId());
            pushDTO.setCorpSecret(userInfo.getCpConfig().getSecret());
            pushDTO.setAgentId(userInfo.getCpConfig().getAgentId());
            sendCpMessage(pushDTO);
        }
    }

    @Override
    public void sendEmail(PushDTO pushDTO) {
        rabbitTemplate.convertAndSend(nameConfig.MAIL_EXCHANGE_NAME,nameConfig.MAIL_ROUTING_KEY,pushDTO);
    }

    @Override
    public void sendMpMessage(PushDTO pushDTO) {
        rabbitTemplate.convertAndSend(nameConfig.MP_EXCHANGE_NAME,nameConfig.MP_ROUTING_KEY,pushDTO);
    }

    @Override
    public void sendCpMessage(PushDTO pushDTO){
        rabbitTemplate.convertAndSend(nameConfig.CP_EXCHANGE_NAME,nameConfig.CP_ROUTING_KEY,pushDTO);
    }
}
