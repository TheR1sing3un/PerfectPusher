package pro.risingsun.push.userservice.service;

import pro.risingsun.push.entity.CpConfig;
import pro.risingsun.push.userservice.model.UserInfoDTO;

/**
 * @author TheR1sing3un
 * @date 2021/10/22 12:59
 * @description
 */

public interface UserService {
    Long login(String openId);

    String updateSendKey(Long id);

    UserInfoDTO getUserInfo(Long id);

    CpConfig updateCpConfig(CpConfig cpConfig);

    Boolean bindMailAddress(Long id);

}
