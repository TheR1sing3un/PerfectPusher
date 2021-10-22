package pro.risingsun.push.userservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.risingsun.push.entity.User;
import pro.risingsun.push.userservice.mapper.UserMapper;
import pro.risingsun.push.userservice.service.UserService;

/**
 * @author TheR1sing3un
 * @date 2021/10/22 12:59
 * @description
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Long login(String openId) {
        log.info(openId);
        User user = userMapper.getUserByOpenId(openId);
        //当数据库没有该用户时,注册该用户
        if (user == null){
            userMapper.insertUser(new User(null,openId,1,0,0));
            user = userMapper.getUserByOpenId(openId);
        }
        return user.getId();
    }

}
