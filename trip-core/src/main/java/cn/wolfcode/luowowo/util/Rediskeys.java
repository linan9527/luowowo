package cn.wolfcode.luowowo.util;


import lombok.Getter;
import lombok.Setter;

/*
* 统一Redis的key管理
* Redis的key定义成枚举类
* 约定每一个key就是枚举类一个实例对象
* */
public enum Rediskeys {
    //短信验证
    VERIFY_CODE("verify_code",Consts.VERIFY_CODE_VAI_TIME*60L);

    @Getter
    @Setter
    private String prefix;//redis的key的前缀
    @Getter
    @Setter
    private Long time;//redis的失效时间，约定单位 秒

    private Rediskeys(String prefix,Long time){
        this.prefix = prefix;
        this.time = time;
    }

    //拼接redis的key传递过来的key可能是多个 使用可变参数来接收
    public String join(String... values){
        StringBuilder sb = new StringBuilder(80);
        sb.append(prefix);
        for (String s: values){
            sb.append(":").append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(Rediskeys.VERIFY_CODE.join("15714268965","user","1"));
    }

}
