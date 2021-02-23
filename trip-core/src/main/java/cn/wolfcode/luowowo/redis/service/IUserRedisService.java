package cn.wolfcode.luowowo.redis.service;

public interface IUserRedisService {
    void setVerifyCode(String phone,String code);
    String getVerifyCode(String phone);
}
