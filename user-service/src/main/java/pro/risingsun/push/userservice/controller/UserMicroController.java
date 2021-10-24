package pro.risingsun.push.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.risingsun.push.model.UserChannelDTO;
import pro.risingsun.push.userservice.service.UserService;

/**
 * @author TheR1sing3un
 * @date 2021/10/24 12:35
 * @description 提供微服务之间调用的用户服务接口
 */

@RestController
@RequestMapping("/mirco/user")
public class UserMicroController {

    @Autowired
    UserService userService;

    @GetMapping("/config/{sendKey}")
    public UserChannelDTO getUserChannelBySendKey(@PathVariable("sendKey") String sendKey){
        System.out.println(sendKey);
        return userService.getUserChannelBySendKey(sendKey);
    }

}
