package cn.wolfcode.luowowo.EnuTest;

public class EnumTe {
    public static void main(String[] args){
        MyDate date1=MyDate.day1;
        System.out.println(date1.getPrefix());
        date1.setPrefix("aaaaa");
        System.out.println(date1.getPrefix());
    }
}
