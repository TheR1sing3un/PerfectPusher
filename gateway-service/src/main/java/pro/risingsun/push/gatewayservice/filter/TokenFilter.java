package pro.risingsun.push.gatewayservice.filter;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import pro.risingsun.push.exception.TokenException;
import pro.risingsun.push.gatewayservice.config.IgnoreProperties;
import pro.risingsun.push.model.CommonResult;
import pro.risingsun.push.utils.JwtUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author TheR1sing3un
 * @date 2021/10/21 21:25
 * @description
 */
@Slf4j
@Component
@AllArgsConstructor
public class TokenFilter implements GlobalFilter, Ordered {

    public static final String token = "token";

    @Autowired
    private JwtUtils jwtUtils;

    private final IgnoreProperties ignoreProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 如果未启用网关验证，则跳过
        if (!ignoreProperties.getEnable()) {
            return chain.filter(exchange);
        }
        log.info("getIgnoreUrl:{}", ignoreProperties.getIgnoreUrl());
        //　如果在忽略的url里，则跳过
        String path = replacePrefix(exchange.getRequest().getURI().getPath());
        String requestUrl = exchange.getRequest().getURI().getRawPath();
        ServerHttpResponse response = exchange.getResponse();
        if (ignore(path) || ignore(requestUrl)) {
            return chain.filter(exchange);
        }
        String headerToken = exchange.getRequest().getHeaders().getFirst(token);
        log.info("token:{}",headerToken);
        try{
            int id = jwtUtils.parserTokenToId(headerToken);
        }catch (TokenException e){
            return authError(response,e);
        }
        return chain.filter(exchange);
    }

    /**
     * 检查是否忽略url
     * @param path 路径
     * @return boolean
     */
    private boolean ignore(String path) {
        return ignoreProperties.getIgnoreUrl().stream()
                .map(url -> url.replace("/**", ""))
                .anyMatch(path::startsWith);
    }

    /**
     * 移除模块前缀
     * @param path 路径
     * @return String
     */
    private String replacePrefix(String path) {
        if (path.startsWith("/mate")) {
            return path.substring(path.indexOf("/",1));
        }
        return path;
    }

    /**
     * 认证错误输出
     * @param resp 响应对象
     * @param tokenException 异常信息
     * @return
     */
    private Mono<Void> authError(ServerHttpResponse resp, TokenException tokenException) {
        resp.setStatusCode(HttpStatus.UNAUTHORIZED);
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        CommonResult result = CommonResult.error().code(tokenException.code).msg(tokenException.message);
        String jsonString = JSONObject.toJSONString(result);
        DataBuffer buffer = resp.bufferFactory().wrap(jsonString.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Flux.just(buffer));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
