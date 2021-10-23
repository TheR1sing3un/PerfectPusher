package pro.risingsun.push.mailservice.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.risingsun.push.model.PushDTO;

import java.io.IOException;

/**
 * @author TheR1sing3un
 * @date 2021/10/23 14:38
 * @description
 */
@Component
public class MailReceiver {

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "MailQueue")
    public void receive(Message message) throws IOException {
        PushDTO pushDTO = objectMapper.readValue(message.getBody(), PushDTO.class);
        System.out.println(pushDTO);
    }
}
