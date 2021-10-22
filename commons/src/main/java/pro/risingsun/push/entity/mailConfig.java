package pro.risingsun.push.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TheR1sing3un
 * @date 2021/10/22 12:26
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class mailConfig {
    private Long userId;
    private String mailAddress;
}
