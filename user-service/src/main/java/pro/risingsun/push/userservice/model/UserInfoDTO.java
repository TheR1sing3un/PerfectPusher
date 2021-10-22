package pro.risingsun.push.userservice.model;

import lombok.*;
import org.apache.ibatis.annotations.Select;
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

    private Integer wxMpStatus;

    private Integer wxCpStatus;

    private Integer mailStatus;

    private CpConfig cpConfig;

    private MailConfig mailConfig;

    private String sendKey;

}
