package cn.wolfcode.luowowo.controller;

import cn.wolfcode.luowowo.service.IUserInfoService;
import cn.wolfcode.luowowo.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  //= @ResponseBody + @Controller
public class LoginController {
    @Autowired
    private IUserInfoService userInfoService;

    //http://localhost:8080/get?id=
    @GetMapping("/get")
    public Object get(String id) {
        return userInfoService.get(id);
    }

    //验证手机号
    @GetMapping("/checkPhone")
    public Object checkPhone(String phone) {
        boolean ret = userInfoService.checkPhone(phone);
        return !ret;
    }

    @Value("${sms.url}")
    private String smsUrl;
    @Value("${sms.appkey}")
    private String appkey;

    //注册发送短信
    @GetMapping("/sendVerifyCode")
    public Object sendVerifyCode(String phone) {
        //调用业务层逻辑，进行短信的发送
       /* JsonResult result = null;
        try {
            //userInfoService.sendVerifyCode(phone);
            userInfoService.sendVerifyCode(phone,smsUrl,appkey);
            result = JsonResult.success();
        } catch (LoginException e) {
            e.printStackTrace();
            result = new JsonResult(JsonResult.CODE_ERROR_PARAM, e.getMessage(), null);
        } catch (Exception e) {
            e.printStackTrace();
            result = new JsonResult(JsonResult.CODE_ERROR, "网络繁忙，请稍后", null);
        }
        return result;*/

       userInfoService.sendVerifyCode(phone,smsUrl,appkey);
       return JsonResult.success();
    }

    //用户注册
    @PostMapping("/userRegist")
    public Object userRegist(String phone,String nickname,
                             String password,String rpassword,String verifyCode){
       /* JsonResult result=null;
        try {
            userInfoService.userRegist(phone,nickname,password,rpassword,verifyCode);
            result=JsonResult.success();
        }catch (LoginException e){
            e.printStackTrace();
            result=new JsonResult(JsonResult.CODE_ERROR,e.getMessage(),null);
        }catch (Exception e){
            e.printStackTrace();
            result=new JsonResult(JsonResult.CODE_ERROR,e.getMessage(),null);
        }
        return result;*/

       //优化后
        userInfoService.userRegist(phone,nickname,password,rpassword,verifyCode);
        return JsonResult.success();
    }

}