package pro.risingsun.push.mpservice.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import pro.risingsun.push.model.CommonResult;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @author TheR1sing3un
 * @date 2021/10/19 14:37
 * @description
 */
@Slf4j
public class ServerEncoder implements Encoder.Text<CommonResult>{

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

    /**
     * 将某类编码成json格式，和实现接口保持类型一致
     * @param commonResult
     * @return
     * @throws EncodeException
     */
    @Override
    public String encode(CommonResult commonResult) throws EncodeException {
        /*
         * 这里是重点，只需要返回Object序列化后的json字符串就行
         * 你也可以使用gosn，fastJson来序列化。
         * 这里我使用fastjson
         */
        try {
            return JSONObject.toJSONString(commonResult);
        }catch (Exception e){
            log.error("",e);
        }
        return null;
    }
}
