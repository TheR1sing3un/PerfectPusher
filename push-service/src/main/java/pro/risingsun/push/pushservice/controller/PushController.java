package pro.risingsun.push.pushservice.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.risingsun.push.model.CommonResult;
import pro.risingsun.push.model.PushDTO;

/**
 * @author TheR1sing3un
 * @date 2021/10/23 12:07
 * @description
 */
@RestController
@RequestMapping("/push")
public class PushController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${mq.mail.exchange-name}")
    private String MAIL_EXCHANGE_NAME;

    @Value("${mq.mail.routing-key}")
    private String MAIL_ROUTING_KEY;

    /**
     * 推送邮件到消息队列
     * @param pushDTO
     * @return
     */
    @PostMapping("/mail")
    public CommonResult pushEmail(@RequestBody PushDTO pushDTO){
        rabbitTemplate.convertAndSend(MAIL_EXCHANGE_NAME,MAIL_ROUTING_KEY,pushDTO);
        return CommonResult.ok();
    }

}
