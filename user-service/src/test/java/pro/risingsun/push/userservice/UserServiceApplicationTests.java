package pro.risingsun.push.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pro.risingsun.push.entity.CpConfig;
import pro.risingsun.push.userservice.mapper.UserMapper;
import pro.risingsun.push.userservice.model.UserInfoDTO;
import pro.risingsun.push.userservice.service.UserService;
import pro.risingsun.push.userservice.utils.RedisUtils;

@SpringBootTest
class UserServiceApplicationTests {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
    }

   /* @Test
    void testRedis(){
        redisUtils.hset("sendKey","1","qwe");
        System.out.println(redisUtils.hget("sendKey", "1"));
    }

    @Test
    void testUserInfoDTO(){
        UserInfoDTO userInfoById = userMapper.getUserInfoById(15l);
        System.out.println(userInfoById);
    }

    @Test
    void testUserExist(){
        System.out.println(userMapper.checkUserExist(15l));
    }*/
}
