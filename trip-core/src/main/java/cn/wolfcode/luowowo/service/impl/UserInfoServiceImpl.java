package cn.wolfcode.luowowo.service.impl;

import cn.wolfcode.luowowo.domain.UserInfo;
import cn.wolfcode.luowowo.exception.LoginException;
import cn.wolfcode.luowowo.redis.service.IUserRedisService;
import cn.wolfcode.luowowo.repository.UserInfoRepository;
import cn.wolfcode.luowowo.service.IUserInfoService;
import cn.wolfcode.luowowo.util.Assertutil;
import cn.wolfcode.luowowo.util.Consts;
import javafx.fxml.LoadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private IUserRedisService userRedisService;

    @Override
    public boolean checkPhone(String phone) {
        UserInfo userInfo=repository.findByPhone(phone);
        return userInfo!=null;
        /*
        * 1.返回LIST
        * 2.返回一个对象，通过判断对象是否为空来判断是否找到该记录
        * */
    }

    @Override
    public UserInfo get(String id) {
        Optional<UserInfo> optional=repository.findById(id);
        //optional.isPresent()  判断是否有值
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }




    //用户注册
    @Override
    public void userRegist(String phone, String nickname,
                           String password, String rpassword, String verifyCode) {
        //判断参数是否为空
        Assertutil.hasLength(phone,"手机号不能为空");
        Assertutil.hasLength(nickname,"昵称不能为空");
        Assertutil.hasLength(password,"密码不能为空");
        Assertutil.hasLength(rpassword,"确认密码不能为空");
        Assertutil.hasLength(verifyCode,"短信验证码不能为空");
       //判断两次密码是否相等
        Assertutil.isEqual(password,rpassword,"两次输入密码必须一致");

        //判断手机号的唯一性
        if (this.checkPhone(phone)){
            throw new LoginException("该手机号已经被注册");
        }
        //手机验证码正确
        String code=userRedisService.getVerifyCode(phone);
        if (code==null||!code.equalsIgnoreCase(verifyCode)){
            throw new LoginException("验证码失效或错误");
        }
        //注册
        UserInfo userInfo=new UserInfo();
        userInfo.setNickname(nickname);
        userInfo.setPhone(phone);
        userInfo.setPassword(password);
        userInfo.setEmail("");
        userInfo.setGender(UserInfo.GENDER_SECRET);
        userInfo.setLevel(0);
        userInfo.setCity("");
        userInfo.setHeadImgUrl("/images/default.jpg");
        userInfo.setInfo("");
        userInfo.setState(UserInfo.STATE_NORMAL);
        //userInfo.setId("");
        repository.save(userInfo);
    }

    /*短信业务类*/
    @Override
    public void sendVerifyCode(String phone, String smsUrl, String appkey) {
        //生成随机验证码
        String code= UUID.randomUUID().toString()
                .replaceAll("-","")
                .substring(0,4);
        //拼接一个信息
        //【中正云通信】您的JD验证码为：6266，欢迎注册使用。
        StringBuilder sb=new StringBuilder(80);
        sb.append("【中正云通信】您的JD验证码为：").append(code).append("请在")
                .append(Consts.VERIFY_CODE_VAI_TIME)
                .append("分钟内使用");
        //发送验证码
        //借助短信网关api发起html请求，使用springmvc提供的类，resttemplate专门发起请求
        RestTemplate restTemplate=new RestTemplate();
        Map map=restTemplate.getForObject(smsUrl, Map.class,phone,sb.toString(),appkey);

        System.out.println(sb.toString());

        if (!map.get("code").equals("10000")){
            throw new LoginException("短信发送失败");
        }
        //将验证码保存到Redis中
        userRedisService.setVerifyCode(phone,code);
    }

}
