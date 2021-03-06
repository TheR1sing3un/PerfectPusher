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
 * @date 2021/10/24 19:53
 * @description
 */

@Configuration
@RefreshScope
public class MpRabbitConfig {

    @Autowired
    private MqNameConfig mqNameConfig;

    /**
     * 注入公众号队列
     * @return
     */
    @Bean
    public Queue mpQueue(){
        return new Queue(mqNameConfig.MP_QUEUE_NAME,true);
    }

    /**
     * 注入公众号消息交换机
     * @return
     */
    @Bean
    DirectExchange mpExchange(){
        return new DirectExchange(mqNameConfig.MP_EXCHANGE_NAME,true,false);
    }

    /**
     * 绑定routingKey
     * @return
     */
    @Bean
    Binding bindingMp(){
        return BindingBuilder.bind(mpQueue()).to(mpExchange()).with(mqNameConfig.MP_ROUTING_KEY);
    }
}
