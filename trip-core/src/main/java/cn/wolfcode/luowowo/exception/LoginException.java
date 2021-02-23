package cn.wolfcode.luowowo.exception;

/*自定义异常
作用：用于区分系统异常和主动抛出的用户异常*/
public class LoginException extends RuntimeException {
    public LoginException(String msg){
        super(msg);
    }
}
