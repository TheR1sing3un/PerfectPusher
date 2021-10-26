package pro.risingsun.push.cpservice.receiver;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.stereotype.Component;
import pro.risingsun.push.cpservice.service.PushCpService;
import pro.risingsun.push.model.PushDTO;

/**
 * @author TheR1sing3un
 * @date 2021/10/26 1:45
 * @description
 */

@Component
@Slf4j
public class CpPushReceiver {

    @Autowired
    PushCpService pushCpService;

    @RabbitListener(queues = "CpQueue")
    public void receive(PushDTO pushDTO) throws WxErrorException {
        pushCpService.pushCpMessage(pushDTO);
    }

}
