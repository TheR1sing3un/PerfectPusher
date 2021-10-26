package pro.risingsun.push.mpservice.service.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.risingsun.push.model.PushDTO;
import pro.risingsun.push.mpservice.config.WxMpConfig;
import pro.risingsun.push.mpservice.service.PushMpService;
import pro.risingsun.push.utils.ColorUtils;
import pro.risingsun.push.utils.PushTextUtils;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author TheR1sing3un
 * @date 2021/10/24 18:59
 * @description
 */
@Service
public class PushMpServiceImpl implements PushMpService {

    @Autowired
    private WxMpConfig wxMpConfig;

    @Autowired
    WxMpService wxMpService;

    @Override
    public void pushMpMessage(PushDTO pushDTO) throws WxErrorException {
        WxMpTemplateMessage wxMpTemplateMessage = WxMpTemplateMessage.builder().toUser(pushDTO.getToUser()).templateId(wxMpConfig.getTemplateId()).build();
        wxMpTemplateMessage.addData(new WxMpTemplateData("text",PushTextUtils.createSimpleText(pushDTO),"#6495ED"));
//        if (pushDTO.getDesc() != null){
//            wxMpTemplateMessage.addData(new WxMpTemplateData("desc",pushDTO.getDesc(),ColorUtils.toHexFromColor(Color.BLUE)));
//        }
//        if (pushDTO.getContent() != null){
//            wxMpTemplateMessage.addData(new WxMpTemplateData("content", pushDTO.getContent(),ColorUtils.toHexFromColor(Color.BLUE)));
//        }
//        wxMpTemplateMessage.addData(new WxMpTemplateData("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))));
//        WxMpKefuMessage mpKefuMessage = WxMpKefuMessage.TEXT().toUser(pushDTO.getToUser()).content(PushTextUtils.createSimpleText(pushDTO)).build();
//        wxMpService.getKefuService().sendKefuMessage(mpKefuMessage);
//        wxMpService.getKefuService().sendKefuMessage(mpKefuMessage);
        wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
    }

}
