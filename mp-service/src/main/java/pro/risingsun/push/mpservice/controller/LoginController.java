package pro.risingsun.push.mpservice.controller;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.risingsun.push.model.CommonResult;
import pro.risingsun.push.mpservice.config.WxMpConfig;
import pro.risingsun.push.mpservice.model.LoginQrcodeDTO;

/**
 * @author TheR1sing3un
 * @date 2021/10/22 13:53
 * @description
 */
@RestController
@RequestMapping("/mp/login")
public class LoginController {

    @Autowired
    WxMpConfig wxMpConfig;

    @Autowired
    WxMpService wxMpService;

    @GetMapping("/qrcode")
    public CommonResult login() throws WxErrorException {
        WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateTmpTicket(wxMpConfig.getSceneStr(), wxMpConfig.getExpire());
        String url = wxMpConfig.getShowCodeUrl()+ticket.getTicket();
        LoginQrcodeDTO loginQrcodeDTO = new LoginQrcodeDTO(url,ticket.getTicket());
        return CommonResult.ok().data(loginQrcodeDTO);
    }
}
