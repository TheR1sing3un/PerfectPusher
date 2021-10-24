package pro.risingsun.push.mpservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.risingsun.push.model.LoginReplyDTO;
import pro.risingsun.push.mpservice.service.LoginService;
import pro.risingsun.push.mpservice.service.UserFeignService;
import pro.risingsun.push.utils.JwtUtils;

/**
 * @author TheR1sing3un
 * @date 2021/10/22 13:14
 * @description
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserFeignService userService;

    @Autowired
    WxMpService wxMpService;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public LoginReplyDTO login(String openId) throws WxErrorException {
        Long id = userService.userLogin(openId);
        //获取用户基本信息
        WxMpUser wxMpUser = wxMpService.getUserService().userInfo(openId);
        log.info("expire: {}",jwtUtils.getExpire());
        //根据id生成token
        String token = jwtUtils.createToken(id);
        log.info("token: {}",token);
        //将用户id,昵称,头像和登录token返回
        LoginReplyDTO replyDTO = new LoginReplyDTO(id,wxMpUser.getNickname(),wxMpUser.getHeadImgUrl(),token);
        return replyDTO;
    }

    /**
     * 从用户模块注册用户并返回id和token
     * @param openId
     * @return
     * @throws WxErrorException
     */

}
