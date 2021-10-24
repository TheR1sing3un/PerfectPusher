package pro.risingsun.push.mailservice.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pro.risingsun.push.mailservice.model.EmailDTO;
import pro.risingsun.push.mailservice.service.EmailService;
import pro.risingsun.push.model.PushDTO;


/**
 * @author TheR1sing3un
 * @date 2021/10/23 14:38
 * @description
 */
@Component
public class MailReceiver {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "MailQueue")
    public void receive(PushDTO pushDTO) {
        EmailDTO emailDTO = new EmailDTO(pushDTO.getToUser(),pushDTO.getTitle(),pushDTO.getContent());
        emailService.sendEmail(emailDTO);
    }
}
