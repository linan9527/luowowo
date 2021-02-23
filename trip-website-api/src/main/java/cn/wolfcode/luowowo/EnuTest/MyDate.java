package cn.wolfcode.luowowo.EnuTest;

/*
* 枚举类型
* 特点：
* 构造都是私有的
* 枚举类型的实例是固定的，当枚举定义好后，实例个数就固定了
* 除了定义好的枚举的属性以外，剩下的操作跟普通类一样get set方法不能放在类上定义，要放在属性上定义
* 对于有参构造的使用，需要更改枚举类型属性的定义方法
* */


import lombok.Getter;
import lombok.Setter;

public enum  MyDate {
    day1("day1",1L),day2("day2",2L),day3("day3",3L);
    @Getter
    @Setter
    private String prefix;
    @Setter
    @Getter
    private Long time;

    private MyDate(String prefix,Long time){
        this.prefix=prefix;
        this.time=time;
    }

}
