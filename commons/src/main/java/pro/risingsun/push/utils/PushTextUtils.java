package pro.risingsun.push.utils;

import com.vdurmont.emoji.EmojiParser;
import pro.risingsun.push.model.PushDTO;

import javax.swing.text.DateFormatter;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author TheR1sing3un
 * @date 2021/10/26 14:32
 * @description 生成推送展示模板
 */

public class PushTextUtils {

    public static final DateTimeFormatter formatter =DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 创建简单推送文本
     * @param pushDTO
     * @return
     */
    public static String createSimpleText(PushDTO pushDTO){
        String origin = String.format(":rocket:您有一条新的推送 \n\n:e-mail:标题: " + pushDTO.getTitle() +
                "\n\n:date:时间: " + LocalDateTime.now().format(formatter) +
                (pushDTO.getDesc() != null ? "\n\n:clipboard:简述: "+pushDTO.getDesc() : "")+
                "\n\n:newspaper:正文: " + pushDTO.getContent() +
                (pushDTO.getUrl() != null ? "\n\n:paperclip:详情: <a href=\""+pushDTO.getUrl()+"\">点击查看详情</a>" : ""));
        String parse = EmojiParser.parseToUnicode(origin);
        return parse;
    }


}
