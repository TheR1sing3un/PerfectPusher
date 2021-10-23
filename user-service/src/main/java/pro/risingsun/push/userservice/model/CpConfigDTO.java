package pro.risingsun.push.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TheR1sing3un
 * @date 2021/10/23 17:27
 * @description 用户个人配置修改企业微信相关配置时的数据传输对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpConfigDTO {
    private Long userId;
    private String corpId;
    private Long agentId;
    private String secret;
    private String target;
    private Boolean wxCpStatus;
}
