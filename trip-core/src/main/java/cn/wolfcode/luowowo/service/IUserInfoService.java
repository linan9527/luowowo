package cn.wolfcode.luowowo.service;

import cn.wolfcode.luowowo.domain.UserInfo;

/*
* 用户服务接口
* */
public interface IUserInfoService {
    /*
    * 用户查询，单查
    * */
    boolean checkPhone(String phone);
    UserInfo get(String id);

    //void sendVerifyCode(String phone);

    void userRegist(String phone, String nickname,
                    String password, String rpassword, String verifyCode);

    void sendVerifyCode(String phone, String smsUrl, String appkey);
}
