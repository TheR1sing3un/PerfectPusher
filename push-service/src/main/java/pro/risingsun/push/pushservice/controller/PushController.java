package pro.risingsun.push.pushservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.risingsun.push.model.CommonResult;
import pro.risingsun.push.model.PushDTO;
import pro.risingsun.push.pushservice.service.PushService;

/**
 * @author TheR1sing3un
 * @date 2021/10/23 12:07
 * @description
 */
@RestController
@RequestMapping("/push")
public class PushController {

    @Autowired
    PushService pushService;


    /**
     * 推送邮件到消息队列
     * @param pushDTO
     * @return
     */
    @PostMapping("/mail")
    public CommonResult pushEmail(@RequestBody PushDTO pushDTO){
        pushService.sendEmail(pushDTO);
        return CommonResult.ok();
    }

    /**
     * 推送用户请求的默认推送请求到各个通道的队列中
     * @param sendKey
     * @param title
     * @param desc
     * @param content
     * @return
     */
    @GetMapping("/{sendKey}")
    public CommonResult push(@PathVariable String sendKey,@RequestParam(value = "title") String title,
                             @RequestParam(value = "desc",required = false) String desc ,
                             @RequestParam(value = "content") String content,
                             @RequestParam(value = "url",required = false)String url){
        PushDTO pushDTO = new PushDTO(title,desc,content,url);
        pushService.pushWithSendKey(sendKey,pushDTO);
        return CommonResult.ok();
    }
}
