package pro.risingsun.push.cpservice.service;

import me.chanjar.weixin.common.error.WxErrorException;
import pro.risingsun.push.model.PushDTO;

/**
 * @author TheR1sing3un
 * @date 2021/10/26 1:46
 * @description
 */

public interface PushCpService {

    void pushCpMessage(PushDTO pushDTO) throws WxErrorException;

}
