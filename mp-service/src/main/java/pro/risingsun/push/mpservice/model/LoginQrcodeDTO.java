package pro.risingsun.push.mpservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TheR1sing3un
 * @date 2021/10/19 11:02
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginQrcodeDTO {
    private String qrcodeUrl;
    private String ticket;
}
