package pro.risingsun.push.pushservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pro.risingsun.push.model.UserChannelDTO;

/**
 * @author TheR1sing3un
 * @date 2021/10/24 12:30
 * @description
 */
@FeignClient("user-service")
public interface UserFeignService {

    @GetMapping("/mirco/user/config/{sendKey}")
    UserChannelDTO getUserChannelBySendKey(@PathVariable("sendKey") String sendKey);
}
