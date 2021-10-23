package pro.risingsun.push.userservice.controller;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.risingsun.push.entity.CpConfig;
import pro.risingsun.push.model.CommonResult;
import pro.risingsun.push.userservice.model.*;
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
     * @param cpConfigDTO
     * @return
     */
    @PutMapping("/cpConfig/{id}")
    public CommonResult updateCpConfig(@PathVariable Long id ,@RequestBody CpConfigDTO cpConfigDTO){
        cpConfigDTO.setUserId(id);
        CpConfigDTO configDTO = userService.updateCpConfig(cpConfigDTO);
        return CommonResult.ok().data(configDTO);
    }

    /**
     * 发送邮箱绑定验证码
     * @param id
     * @param mailAddress
     * @return
     */
    @PostMapping("/mail/{id}")
    public CommonResult bindMailAddress(@PathVariable Long id,@RequestBody MailAddressDTO mailAddress){
        userService.bindMailAddress(id,mailAddress.getMailAddress());
        return CommonResult.ok();
    }

    /**
     * 验证邮箱绑定验证码
     * @param id
     * @param mailCheckDTO
     * @return
     */
    @PutMapping("/mail/{id}")
    public CommonResult checkMailCode(@PathVariable Long id, @RequestBody MailCheckDTO mailCheckDTO){
        UserMailDTO userMailDTO = userService.checkMailCode(mailCheckDTO.getCode(), new UserMailDTO(id,mailCheckDTO.getMailAddress()));
        return CommonResult.ok().data(userMailDTO);
    }

    /**
     * 更新官方公众号推送开关
     * @param id
     * @return
     */
    @PostMapping("/mpStatus/{id}")
    public CommonResult updateMpStatus(@PathVariable("id") Long id){
        userService.updateMpStatus(id);
        return CommonResult.ok();
    }

    /**
     * 解绑邮箱
     * @param id
     * @return
     */
    @DeleteMapping("/mail/{id}")
    public CommonResult deleteMailConfig(@PathVariable("id") Long id){
        userService.deleteMailConfig(id);
        return CommonResult.ok();
    }

    /**
     * 更新邮箱推送开关
     * @param id
     * @return
     */
    @PostMapping("/mailStatus/{id}")
    public CommonResult updateMailStatus(@PathVariable("id") Long id){
        userService.updateMailStatus(id);
        return CommonResult.ok();
    }
}
