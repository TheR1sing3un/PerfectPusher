package pro.risingsun.push.userservice.service;

import pro.risingsun.push.entity.User;
import pro.risingsun.push.model.UserChannelDTO;
import pro.risingsun.push.userservice.model.CpConfigDTO;
import pro.risingsun.push.model.UserInfoDTO;
import pro.risingsun.push.userservice.model.UserMailDTO;

/**
 * @author TheR1sing3un
 * @date 2021/10/22 12:59
 * @description
 */

public interface UserService {
    Long login(String openId);

    String updateSendKey(Long id);

    UserInfoDTO getUserInfo(Long id);

    CpConfigDTO updateCpConfig(CpConfigDTO cpConfigDTO);

    void bindMailAddress(Long id,String mailAddress);

    UserMailDTO checkMailCode(Integer code,UserMailDTO userMailDTO);

    void updateMpStatus(Long id);

    void updateMailStatus(Long id);

    void deleteMailConfig(Long id);

    UserChannelDTO getUserChannelBySendKey(String sendKey);
}
