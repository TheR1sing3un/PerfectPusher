package pro.risingsun.push.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TheR1sing3un
 * @date 2021/10/23 11:58
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PushDTO {

    private String toUser;

    private String type;

    private String title;

    private String desc;

    private String content;

    public PushDTO(String title,String desc,String content){
        this.title = title;
        this.desc = desc;
        this.content = content;
    }

}
