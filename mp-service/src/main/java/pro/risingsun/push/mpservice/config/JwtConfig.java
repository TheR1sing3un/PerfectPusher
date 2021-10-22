package pro.risingsun.push.mpservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pro.risingsun.push.utils.JwtUtils;

/**
 * @author TheR1sing3un
 * @date 2021/10/21 18:33
 * @description
 */
@Configuration
public class JwtConfig {

    @Value("${jwt.secret:TheR1sing3un}")
    private String secret;

    @Value("${jwt.expire:7200}")
    private Long expire;

    @Bean
    public JwtUtils jwtUtils(){
        return new JwtUtils(secret,expire);
    }
}
