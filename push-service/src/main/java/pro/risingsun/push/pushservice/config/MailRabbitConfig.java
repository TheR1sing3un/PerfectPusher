package pro.risingsun.push.pushservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author TheR1sing3un
 * @date 2021/10/23 12:10
 * @description
 */
@Configuration
@RefreshScope
public class MailRabbitConfig {

    @Autowired
    private MqNameConfig mqNameConfig;

    /**
     * 注入邮件队列
     * @return
     */
    @Bean
    public Queue mailQueue(){
        return new Queue(mqNameConfig.MAIL_QUEUE_NAME,true);
    }

    /**
     * 注入邮件消息交换机
     * @return
     */
    @Bean
    DirectExchange mailExchange(){
        return new DirectExchange(mqNameConfig.MAIL_EXCHANGE_NAME,true,false);
    }

    /**
     * 绑定routingKey
     * @return
     */
    @Bean
    Binding bindingMail(){
        return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(mqNameConfig.MAIL_ROUTING_KEY);
    }
}
