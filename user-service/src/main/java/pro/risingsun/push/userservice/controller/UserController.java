package pro.risingsun.push.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.risingsun.push.userservice.service.UserService;

/**
 * @author TheR1sing3un
 * @date 2021/10/22 12:52
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 用户登录服务,若用户未注册则自动注册,并且返回一个用户登陆后的基本信息对象
     * @param openId
     * @return
     */
    @PostMapping("/login")
    public Long userLogin(@RequestParam String openId) {
        return userService.login(openId);
    }

}
