package pro.risingsun.push.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TheR1sing3un
 * @date 2021/10/19 15:52
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginReplyDTO {

    private Long id;
    private String nickname;
    private String headImgUrl;
    private String token;

}
