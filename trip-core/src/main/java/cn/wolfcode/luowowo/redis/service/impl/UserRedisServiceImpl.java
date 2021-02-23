package cn.wolfcode.luowowo.redis.service.impl;

import cn.wolfcode.luowowo.redis.service.IUserRedisService;
import cn.wolfcode.luowowo.util.Consts;
import cn.wolfcode.luowowo.util.Rediskeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserRedisServiceImpl implements IUserRedisService {
    @Autowired
    private StringRedisTemplate template;

    //保存验证码
    @Override
    public void setVerifyCode(String phone, String code) {
       /* String key="verify_code"+phone;
        template.opsForValue().set(key,code,
                Consts.VERIFY_CODE_VAI_TIME*60, TimeUnit.SECONDS);*/

       String key= Rediskeys.VERIFY_CODE.join(phone);
       template.opsForValue().set(key,code,Rediskeys.VERIFY_CODE.getTime(),TimeUnit.SECONDS);
    }

    //验证码是否失效或错误
    @Override
    public String getVerifyCode(String phone) {
        //String key="verify_code"+phone;
        String key=Rediskeys.VERIFY_CODE.join(phone);
        return template.opsForValue().get(key);
    }
}
