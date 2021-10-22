package pro.risingsun.push.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.risingsun.push.model.CommonResult;

import javax.annotation.Resource;

/**
 * @author TheR1sing3un
 * @date 2021/10/21 22:18
 * @description
 */
@RestController
public class TestController {

    @GetMapping("/user/test")
    public CommonResult testGateway(){
        return CommonResult.ok().data("user");
    }
}
