package cn.wolfcode.luowowo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
@Document("userInfo")
public class UserInfo extends BaseDomain {
    public static final int GENDER_MALE=1;//男
    public static final int  GENDER_FEMALE=2;//女
    public static final int  GENDER_SECRET=0;//保密
    public static final int STATE_NORMAL=0;//正常
    public static final int STATE_DISABLE=1;//冻结

    private String nickname;//昵称
    private String phone;//手机
    private String email;//邮箱
    private String password;//密码
    private int gender=GENDER_SECRET;//性别
    private String city;//所在城市
    private int level=0;//等级
    private String headImgUrl;//头像
    private String info;//个性签名
    private int state=STATE_NORMAL;//状态
}
