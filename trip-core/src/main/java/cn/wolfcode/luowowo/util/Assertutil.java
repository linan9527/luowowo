package cn.wolfcode.luowowo.util;

import cn.wolfcode.luowowo.exception.LoginException;

/*参数断言工具类*/
public class Assertutil {
    /*判断传入的参数是否有值*/
    public static void hasLength(String value,String msg) {
        if (value == null || "".equals(value.trim())) {
            throw new LoginException(msg);
        }
    }
        public static void isEqual(String v1,String v2,String msg){
            if (v1==null||v2==null){
                throw new LoginException("判断的参数不能为空");
            }
            if (!v1.equals(v2)){
                throw new LoginException(msg);
            }

        }




}
