package pro.risingsun.push.userservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.risingsun.push.entity.User;
import pro.risingsun.push.model.PushDTO;
import pro.risingsun.push.model.UserChannelDTO;
import pro.risingsun.push.userservice.exception.UserException;
import pro.risingsun.push.userservice.mapper.UserMapper;
import pro.risingsun.push.userservice.model.CpConfigDTO;
import pro.risingsun.push.model.UserInfoDTO;
import pro.risingsun.push.userservice.model.UserMailDTO;
import pro.risingsun.push.userservice.service.PushFeignService;
import pro.risingsun.push.userservice.service.UserService;
import pro.risingsun.push.userservice.utils.RedisUtils;
;
import java.util.Random;
import java.util.UUID;

/**
 * @author TheR1sing3un
 * @date 2021/10/22 12:59
 * @description
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    //Redis存储senKey的哈希表的key
    public static final String SEND_KEY_KEY_NAME = "sendKey";

    //Redis存储邮箱验证码的哈希表的key
    public static final String MAIL_CODE_KEY_NAME = "mailCode";

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    PushFeignService pushFeignService;

    @Override
    public Long login(String openId) {
        User user = userMapper.getUserByOpenId(openId);
        //当数据库没有该用户时,注册该用户
        if (user == null){
            User newUser = new User(openId);
            //设置sendKey
            String sendKey = createSendKey();
            newUser.setSendKey(sendKey);
            //插入到数据库中
            userMapper.insertUser(newUser);
            user = userMapper.getUserByOpenId(openId);
        }
        return user.getId();
    }

    @Override
    public String updateSendKey(Long id) {
        String sendKey = createSendKey();
        userMapper.updateSendKey(id,sendKey);
        return sendKey;
    }

    @Override
    public UserInfoDTO getUserInfo(Long id) {
        UserInfoDTO userInfoById = userMapper.getUserInfoById(id);
        return userInfoById;
    }

    @Override
    public UserChannelDTO getUserChannelBySendKey(String sendKey){
        UserChannelDTO user  = userMapper.getUserInfoBySendKey(sendKey);
        return user;
    }

    /**
     * 更新用户的企业微信配置
     * @param cpConfigDTO
     * @return
     */
    @Override
    @Transactional
    public CpConfigDTO updateCpConfig(CpConfigDTO cpConfigDTO) {
        if (!userMapper.checkUserExist(cpConfigDTO.getUserId())){
            throw new UserException(UserException.USER_NO_EXIST,"用户不存在");
        }
        //更新cp_config
        userMapper.updateCpConfig(cpConfigDTO);
        //更新wx_cp_status
        userMapper.updateCpStatus(cpConfigDTO);
        return cpConfigDTO;
    }

    /**
     * 绑定邮箱
     * @param id
     * @return
     */
    @Override
    public void bindMailAddress(Long id,String mailAddress) {
        Random random = new Random();
        //生成6位随机验证码
        Integer code = random.nextInt(900000) + 99999;
        PushDTO pushDTO = new PushDTO(mailAddress,"PerfectPusher邮箱绑定验证","你正在PerfectPusher中绑定邮箱","验证码 : "+code.toString());
        //调用推送服务进行邮件推送
        pushFeignService.pushEmail(pushDTO);
        //将验证码信息存入redis,过期时间5分钟
        redisUtils.hset(MAIL_CODE_KEY_NAME,mailAddress,code.toString(),300);
    }

    /**
     * 验证邮箱验证码
     * @param userMailDTO
     * @param code
     * @return
     */
    @Override
    public UserMailDTO checkMailCode(Integer code,UserMailDTO userMailDTO) {
        String mailCode = (String) redisUtils.hget(MAIL_CODE_KEY_NAME, userMailDTO.getMailAddress());
        //当该用户的邮箱验证码不存在或者验证码错误的时候
        if (mailCode == null || !mailCode.equals(code.toString())){
            throw new UserException(UserException.MAIL_CODE_ERROR,"邮箱验证码错误或已过期");
        }else {
            //验证通过则更新用户的邮箱地址
            userMapper.insertEmailAddress(userMailDTO);
            return userMailDTO;
        }
    }

    /**
     * 更新官方公众号开关
     * @param id
     */
    @Override
    public void updateMpStatus(Long id) {
        //更新状态为原先相反的状态
        userMapper.updateMpStatus(id,!userMapper.getMpStatus(id));
    }

    /**
     * 更新邮箱通道开关
     * @param id
     */
    @Override
    public void updateMailStatus(Long id){
        //查看原先状态
        Boolean mailStatus = userMapper.getMailStatus(id);
        //如果本来是未开启的
        if (!mailStatus){
            //查看是否已有绑定邮箱
            Boolean configExist = userMapper.checkMailConfigExist(id);
            //如果没有绑定邮箱,但是现在尝试打开邮箱通道开关则报错
            if (!configExist){
                throw new UserException(UserException.MAIL_NO_BIND,"用户未绑定邮箱");
            }
        }
        //更新邮箱通道开关状态
        userMapper.updateMailStatus(id,!mailStatus);
    }

    /**
     * 解绑邮箱
     * @param id
     */
    @Override
    public void deleteMailConfig(Long id) {
        //查看原本是否有绑定邮箱
        Boolean configExist = userMapper.checkMailConfigExist(id);
        if (!configExist) {
            //如果没有绑定则报错
            throw new UserException(UserException.MAIL_NO_BIND,"用户未绑定邮箱");
        }
        //删除绑定邮箱
        userMapper.deleteMailConfig(id);
    }

    private String createSendKey(){
        String string = UUID.randomUUID().toString();
        String sendKey = string.replaceAll("-", "");
        return sendKey;
    }

}
