package pro.risingsun.push.mailservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TheR1sing3un
 * @date 2021/10/23 14:55
 * @description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {

    //目标邮箱
    private String target;

    //邮件主题
    private String topic;

    //邮件正文
    private String content;

}
