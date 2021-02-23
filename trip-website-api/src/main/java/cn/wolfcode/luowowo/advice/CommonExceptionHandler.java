package cn.wolfcode.luowowo.advice;

import cn.wolfcode.luowowo.exception.LoginException;
import cn.wolfcode.luowowo.util.JsonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*通过事务的方式，动态代理或者叫切面方式。
对controller中的请求方法出现的异常机制进行统一规划
@ControllerAdvice:是controller类的功能增强标签
在请求方法处理之前可以对请求方法进行功能增强
在求情方法处理之后可以请求对请求方法进行功能增强，例如：请求方法统一异常处理。
*/

@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public Object displayExp(LoginException e){
        e.printStackTrace();
        return JsonResult.Error(JsonResult.CODE_ERROR,e.getMessage(),null);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Object runTimeExp(Exception e){
        e.printStackTrace();
        return JsonResult.defaultError();
    }
}
