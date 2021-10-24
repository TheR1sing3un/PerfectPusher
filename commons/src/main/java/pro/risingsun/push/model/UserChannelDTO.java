package pro.risingsun.push.model;

import jdk.jfr.MemoryAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.risingsun.push.entity.CpConfig;
import pro.risingsun.push.entity.MailConfig;

/**
 * @author TheR1sing3un
 * @date 2021/10/24 14:40
 * @description 用户关于推送通道的所有信息
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChannelDTO {

    private Long id;

    private String openId;

    private Boolean wxMpStatus;

    private Boolean wxCpStatus;

    private Boolean mailStatus;

    private CpConfig cpConfig;

    private MailConfig mailConfig;

    private String sendKey;
}
