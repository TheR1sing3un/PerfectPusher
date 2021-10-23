package pro.risingsun.push.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TheR1sing3un
 * @date 2021/10/23 17:12
 * @description
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MailCheckDTO {

    private Integer code;

    private String mailAddress;
}
