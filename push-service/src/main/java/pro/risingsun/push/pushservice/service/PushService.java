package pro.risingsun.push.pushservice.service;

import pro.risingsun.push.model.PushDTO;

/**
 * @author TheR1sing3un
 * @date 2021/10/24 12:22
 * @description
 */

public interface PushService {

    void pushWithSendKey(String sendKey, PushDTO pushDTO);

    void sendEmail(PushDTO pushDTO);

    void sendMpMessage(PushDTO pushDTO);
}
