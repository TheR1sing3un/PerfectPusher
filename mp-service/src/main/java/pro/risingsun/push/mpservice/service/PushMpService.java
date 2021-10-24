package pro.risingsun.push.mpservice.service;

import me.chanjar.weixin.common.error.WxErrorException;
import pro.risingsun.push.model.PushDTO;

/**
 * @author TheR1sing3un
 * @date 2021/10/24 18:51
 * @description
 */

public interface PushMpService {


    void pushMpMessage(PushDTO pushDTO) throws WxErrorException;

}
