package pro.risingsun.push.gatewayservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.risingsun.push.model.CommonResult;
import pro.risingsun.push.utils.JwtUtils;

/**
 * @author TheR1sing3un
 * @date 2021/10/21 18:12
 * @description
 */
@RestController
@RefreshScope
public class TestController {

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/test/config")
    public CommonResult getSecret(){
        return CommonResult.ok().data(jwtUtils.getSecret());
    }

}
