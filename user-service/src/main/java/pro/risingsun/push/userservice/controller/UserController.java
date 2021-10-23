package pro.risingsun.push.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.risingsun.push.entity.CpConfig;
import pro.risingsun.push.model.CommonResult;
import pro.risingsun.push.userservice.model.UserInfoDTO;
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

    /**
     * 用户重置SendKey服务
     * @param id
     * @return
     */
    @PutMapping("/sendKey/{id}")
    public CommonResult updateSendKey(@PathVariable("id") Long id){
        String sendKey = userService.updateSendKey(id);
        return CommonResult.ok().data(sendKey);
    }

    /**
     * 用户获取个人配置信息
     * @param id
     * @return
     */
    @GetMapping("/config/{id}")
    public CommonResult getUserInfoConfig(@PathVariable("id") Long id){
        UserInfoDTO userInfo = userService.getUserInfo(id);
        return CommonResult.ok().data(userInfo);
    }

    /**
     * 用户修改个人企业微信相关配置信息
     * @param cpConfig
     * @return
     */
    @PutMapping("/cpConfig/{id}")
    public CommonResult updateCpConfig(@PathVariable Long id ,@RequestBody CpConfig cpConfig){
        cpConfig.setUserId(id);
        CpConfig config = userService.updateCpConfig(cpConfig);
        return CommonResult.ok().data(config);
    }

    @PutMapping("/mail/{id}")
    public CommonResult bindMailAddress(@PathVariable Long id){
        userService.bindMailAddress(id);
        return CommonResult.ok();
    }

    @PostMapping("/mail/{id}")
    public CommonResult checkMailCode(@PathVariable Long id,@RequestBody Integer code){
        return CommonResult.ok();
    }
}
