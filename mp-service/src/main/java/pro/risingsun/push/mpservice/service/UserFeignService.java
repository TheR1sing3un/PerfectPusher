package pro.risingsun.push.mpservice.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author TheR1sing3un
 * @date 2021/10/22 12:41
 * @description
 */
@FeignClient("user-service")
public interface UserFeignService {

    @PostMapping("/user/login")
    Long userLogin(@RequestParam("openId") String openId);

}
