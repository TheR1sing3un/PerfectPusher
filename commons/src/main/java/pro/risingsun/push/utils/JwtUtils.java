package pro.risingsun.push.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pro.risingsun.push.exception.TokenException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TheR1sing3un
 * @date 2021/10/21 16:54
 * @description
 */
@Data
@AllArgsConstructor
public class JwtUtils {

    //定义加密密匙
    public String secret = "lcy";
    //定义多少秒过期
    private Long expire = 10000l;


    /**
     * 根据传进来的user对象产生token
     * @param id
     * @return token 返回生成的token
     */
    public String createToken(Long id) {
        //定义过期时间
        Date expiredDate = new Date(System.currentTimeMillis() + expire * 1000);
        //定义头部信息
        Map<String, Object> map = new HashMap<>();
        map.put("typ", "JWT");//添加头部参数
        map.put("alg", "HS256");//添加头部参数,加密方式
        //创建token
        String token = JWT.create()
                .withIssuedAt(new Date())//添加签发日期
                .withClaim("id",id)//添加用户加密的参数
                .withExpiresAt(expiredDate)//添加过期时间
                .withHeader(map)//添加头部信息
                .sign(Algorithm.HMAC256(secret));
        return token;
    }

    /**
     * 根据token产生可解析出传入token数据的claim的集合
     * @param token
     * @return 一个可以解析token数据的map
     */
    public Map<String, Claim> verifyToken(String token){
        DecodedJWT jwt = null;
        try{
            //获取jwt解析器
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            jwt = verifier.verify(token);
        }catch (TokenExpiredException tokenExpiredException){
            throw new TokenException(TokenException.TOKEN_EXPIRED,"token已经过期");
        }catch (Exception e){
            e.printStackTrace();
            throw new TokenException(TokenException.TOKEN_VERIFY_ERROR,"token解析异常");
        }
        return jwt.getClaims();
    }

    /**
     * 验证token并返回token中存入的id信息
     * @param token
     * @return
     */
    public int parserTokenToId(String token){
        //token为空时,抛出异常
        if (token == null){
            throw new TokenException(TokenException.NO_TOKEN,"请求未携带token");
        }
        //获取该token解析出来的集合
        Map<String, Claim> stringClaimMap = verifyToken(token);
        //解析出id
        Claim id = stringClaimMap.get("id");
        //id为空,token数据异常
        if(id == null){
            throw new TokenException(TokenException.TOKEN_PARSER_ERROR,"token解析获取数据异常");
        }
        return id.asInt();
    }
}
