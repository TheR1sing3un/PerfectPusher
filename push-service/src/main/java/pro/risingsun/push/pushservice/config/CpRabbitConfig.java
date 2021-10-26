package pro.risingsun.push.pushservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author TheR1sing3un
 * @date 2021/10/26 1:41
 * @description
 */
@Configuration
@RefreshScope
public class CpRabbitConfig {

    @Autowired
    private MqNameConfig mqNameConfig;

    /**
     * 注入企业微信队列
     * @return
     */
    @Bean
    public Queue cpQueue(){
        System.out.println("cpqueue"+mqNameConfig.CP_QUEUE_NAME);
        System.out.println("cpexcahnge"+mqNameConfig.CP_EXCHANGE_NAME);
        System.out.println("mpqueue"+mqNameConfig.MP_QUEUE_NAME);
        return new Queue(mqNameConfig.CP_QUEUE_NAME,true);
    }

    /**
     * 注入企业微信消息交换机
     * @return
     */
    @Bean
    DirectExchange cpExchange(){
        return new DirectExchange(mqNameConfig.CP_EXCHANGE_NAME,true,false);
    }

    /**
     * 绑定routingKey
     * @return
     */
    @Bean
    Binding bindingCp(){
        return BindingBuilder.bind(cpQueue()).to(cpExchange()).with(mqNameConfig.CP_ROUTING_KEY);
    }
}
