package pro.risingsun.push.userservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.risingsun.push.entity.CpConfig;
import pro.risingsun.push.entity.User;
import pro.risingsun.push.userservice.exception.UserException;
import pro.risingsun.push.userservice.mapper.UserMapper;
import pro.risingsun.push.userservice.model.UserInfoDTO;
import pro.risingsun.push.userservice.service.UserService;
import pro.risingsun.push.userservice.utils.RedisUtils;

import java.sql.SQLException;
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
    public static final String keyName = "sendKey";

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public Long login(String openId) {
        User user = userMapper.getUserByOpenId(openId);
        //当数据库没有该用户时,注册该用户
        if (user == null){
            userMapper.insertUser(new User(null,openId,1,0,0));
            user = userMapper.getUserByOpenId(openId);
            String sendKey = createSendKey();
            redisUtils.hset(keyName,user.getId().toString(),sendKey);
        }
        return user.getId();
    }

    @Override
    public String updateSendKey(Long id) {
        String sendKey = createSendKey();
        redisUtils.hset(keyName,id.toString(),sendKey);
        return sendKey;
    }

    @Override
    public UserInfoDTO getUserInfo(Long id) {
        UserInfoDTO userInfoById = userMapper.getUserInfoById(id);
        userInfoById.setSendKey(redisUtils.hget(keyName,id.toString()).toString());
        return userInfoById;
    }

    @Override
    public CpConfig updateCpConfig(CpConfig cpConfig) {
        if (!userMapper.checkUserExist(cpConfig.getUserId())){
            throw new UserException(UserException.USER_NO_EXIST,"用户不存在");
        }
        userMapper.updateCpConfig(cpConfig);
        return cpConfig;
    }

    /**
     * 绑定邮箱
     * @param id
     * @return
     */
    @Override
    public Boolean bindMailAddress(Long id) {

        return null;
    }


    private String createSendKey(){
        String string = UUID.randomUUID().toString();
        String sendKey = string.replaceAll("-", "");
        return sendKey;
    }

}
