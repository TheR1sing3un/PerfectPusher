package pro.risingsun.push.pushservice.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: TheR1sing3un
 * @Date: 2021/09/21/12:05
 * @Description: 配置RabbitTemplate(回调)
 */
@Configuration
public class RabbitMQConfig {


        @Bean
        public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
            final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
            rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
            return rabbitTemplate;
        }

        @Bean
        public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
            return new Jackson2JsonMessageConverter();
        }



}
