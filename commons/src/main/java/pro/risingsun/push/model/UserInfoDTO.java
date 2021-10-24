package pro.risingsun.push.model;

import lombok.*;
import pro.risingsun.push.entity.CpConfig;
import pro.risingsun.push.entity.MailConfig;

/**
 * @author TheR1sing3un
 * @date 2021/10/22 21:48
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {

    private Long id;

    private Boolean wxMpStatus;

    private Boolean wxCpStatus;

    private Boolean mailStatus;

    private CpConfig cpConfig;

    private MailConfig mailConfig;

    private String sendKey;

}
