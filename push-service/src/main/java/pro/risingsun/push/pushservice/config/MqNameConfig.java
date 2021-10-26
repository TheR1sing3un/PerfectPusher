package pro.risingsun.push.pushservice.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author TheR1sing3un
 * @date 2021/10/26 1:32
 * @description
 */

@Configuration
public class MqNameConfig {

    @Value("${mq.mail.queue-name}")
    public String MAIL_QUEUE_NAME;

    @Value("${mq.mail.exchange-name}")
    public String MAIL_EXCHANGE_NAME;

    @Value("${mq.mail.routing-key}")
    public String MAIL_ROUTING_KEY;

    @Value("${mq.mp.queue-name}")
    public String MP_QUEUE_NAME;

    @Value("${mq.mp.exchange-name}")
    public String MP_EXCHANGE_NAME;

    @Value("${mq.mp.routing-key}")
    public String MP_ROUTING_KEY;

    @Value("${mq.cp.queue-name}")
    public String CP_QUEUE_NAME;

    @Value("${mq.cp.exchange-name}")
    public String CP_EXCHANGE_NAME;

    @Value("${mq.cp.routing-key}")
    public String CP_ROUTING_KEY;

}
