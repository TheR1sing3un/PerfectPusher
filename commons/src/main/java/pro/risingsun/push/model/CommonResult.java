package pro.risingsun.push.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TheR1sing3un
 * @date 2021/10/2 22:42
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    public static final Integer SUCCESS = 20000;

    public static final Integer ERROR = 40000;

    private Integer code;

    private String message;

    private T data;

    public static CommonResult ok(){
        return new CommonResult(SUCCESS,"success",null);
    }

    public static CommonResult error(){
        return new CommonResult(ERROR,"error",null);
    }

    public CommonResult msg(String message){
        this.message = message;
        return this;
    }

    public CommonResult code(int code){
        this.code = code;
        return this;
    }

    public CommonResult<T> data(T data){
        this.data = data;
        return this;
    }

}

