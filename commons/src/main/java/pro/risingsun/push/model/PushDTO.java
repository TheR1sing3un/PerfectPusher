package pro.risingsun.push.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.InterfaceAddress;

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

    private String corpId;

    private String CorpSecret;

    private Integer AgentId;

    private String url;

    public PushDTO(String title,String desc,String content,String url){
        this.title = title;
        this.desc = desc;
        this.content = content;
        this.url = url;
    }
    public PushDTO(String toUser,String title,String desc,String content,String url){
        this.toUser = toUser;
        this.title = title;
        this.desc = desc;
        this.content = content;
        this.url = url;
    }

}
