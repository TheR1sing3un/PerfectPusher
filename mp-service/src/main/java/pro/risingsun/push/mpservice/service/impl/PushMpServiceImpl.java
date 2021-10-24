package pro.risingsun.push.mpservice.service.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.risingsun.push.model.PushDTO;
import pro.risingsun.push.mpservice.service.PushMpService;

import java.awt.*;

/**
 * @author TheR1sing3un
 * @date 2021/10/24 18:59
 * @description
 */
@Service
public class PushMpServiceImpl implements PushMpService {

    @Autowired
    WxMpService wxMpService;

    @Override
    public void pushMpMessage(PushDTO pushDTO) throws WxErrorException {
        WxMpTemplateMessage wxMpTemplateMessage = WxMpTemplateMessage.builder().toUser(pushDTO.getToUser()).templateId("wryAo4Jlhn2o8CzWHkJF7pO2JeNFEkEg86i8LFx-Fq8").build();
        wxMpTemplateMessage.addData(new WxMpTemplateData("title",pushDTO.getTitle(), Color.BLUE.toString()));
        if (pushDTO.getDesc() != null){
            wxMpTemplateMessage.addData(new WxMpTemplateData("desc",pushDTO.getDesc(),Color.BLUE.toString()));
        }
        if (pushDTO.getContent() != null){
            wxMpTemplateMessage.addData(new WxMpTemplateData("content", pushDTO.getContent(),Color.BLUE.toString()));
        }
        wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
    }

}
