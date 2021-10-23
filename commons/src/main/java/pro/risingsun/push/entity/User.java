package pro.risingsun.push.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.InvocationTargetException;

/**
 * @author TheR1sing3un
 * @date 2021/10/19 13:34
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String openId;

    private Boolean wxMpStatus;

    private Boolean wxCpStatus;

    private Boolean mailStatus;

    public User(String openId){
        this.id = null;
        this.openId = openId;
        this.wxMpStatus = true;
        this.wxCpStatus = false;
        this.mailStatus = false;
    }

}
