package pro.risingsun.push.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TheR1sing3un
 * @date 2021/10/22 12:19
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpConfig {
    private Long userId;
    private String corpId;
    private Integer agentId;
    private String secret;
    private String target;
}
