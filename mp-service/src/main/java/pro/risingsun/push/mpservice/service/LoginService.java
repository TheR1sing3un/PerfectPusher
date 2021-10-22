package pro.risingsun.push.mpservice.service;

import me.chanjar.weixin.common.error.WxErrorException;
import pro.risingsun.push.model.LoginReplyDTO;

/**
 * @author TheR1sing3un
 * @date 2021/10/22 13:14
 * @description
 */

public interface LoginService {
    LoginReplyDTO login(String openId) throws WxErrorException;
}
