package pro.risingsun.push.userservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import pro.risingsun.push.model.CommonResult;
import pro.risingsun.push.model.PushDTO;

/**
 * @author TheR1sing3un
 * @date 2021/10/23 15:35
 * @description
 */
@FeignClient("push-service")
public interface PushFeignService {

    @PostMapping("/push/mail")
    CommonResult pushEmail(PushDTO pushDTO);

}
