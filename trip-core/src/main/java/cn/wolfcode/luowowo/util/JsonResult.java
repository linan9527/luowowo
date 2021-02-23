package cn.wolfcode.luowowo.util;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class JsonResult<T> {
    public static final int CODE_SUCCESS=200;
    public static final String MSG_SUCCESS="操作成功";

    public static final int CODE_NOLOGIN=401;
    public static final String MSG_NOLOGIN="请先登录";

    public static final int CODE_ERROR=500;
    public static final String MSG_ERROR="系统异常，请联系管理员";

    public static final int CODE_ERROR_PARAM=501;
    public static final String MSG_ERROR_FEILE="短信发送失败";

    private int code;
    private String msg;
    private T data;

    public JsonResult(int code,String msg,T data){
        this.code=code;
        this.msg=msg;
        this.data=data;
    }

    public static <T> JsonResult success(){
        return new JsonResult(CODE_SUCCESS,MSG_SUCCESS,null);
    }

    public static <T> JsonResult defaultError(){
        return new JsonResult(CODE_ERROR,MSG_ERROR,null);
    }

    public static <T> JsonResult nologin(){
        return new JsonResult(CODE_NOLOGIN,MSG_NOLOGIN,null);
    }

    public static <T> Object Error(int codeError,String message,T data){
        return new JsonResult(codeError,message,data);
    }
}
