package pro.risingsun.push.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TheR1sing3un
 * @date 2021/10/23 15:59
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMailDTO {

    private Long id;

    private String mailAddress;
}
