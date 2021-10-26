package pro.risingsun.push.cpservice.service.impl;

import com.vdurmont.emoji.EmojiParser;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.springframework.stereotype.Service;
import pro.risingsun.push.cpservice.service.PushCpService;
import pro.risingsun.push.model.PushDTO;
import pro.risingsun.push.utils.PushTextUtils;

/**
 * @author TheR1sing3un
 * @date 2021/10/26 1:46
 * @description
 */
@Service
public class PushCpServiceImpl implements PushCpService {


    @Override
    public void pushCpMessage(PushDTO pushDTO) {
        WxCpService wxCpService = config(pushDTO);
        String text = PushTextUtils.createSimpleText(pushDTO);
        WxCpMessage message = WxCpMessage.TEXT().agentId(wxCpService.getWxCpConfigStorage().getAgentId()).toUser(pushDTO.getToUser()).content(text).build();
        try {
            wxCpService.getMessageService().send(message);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从pushDTO中组装出WxCpService
     *
     * @param pushDTO
     * @return
     */
    private WxCpService config(PushDTO pushDTO) {
        //装配wxCp配置
        WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
        config.setCorpId(pushDTO.getCorpId());
        config.setAgentId(pushDTO.getAgentId());
        config.setCorpSecret(pushDTO.getCorpSecret());
        //将配置装配到wxCpService中
        WxCpServiceImpl wxCpService = new WxCpServiceImpl();
        wxCpService.setWxCpConfigStorage(config);
        return wxCpService;
    }

}
