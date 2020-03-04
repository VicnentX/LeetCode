package leetcode.concurrency.singleton;

/**
 * /**
 *  * target: 饿汉式 （静态常量）
 *  *
 *  * steps：
 *  * 1. 构造器私有
 *  * 2。 定义一个静态常量保存一个唯一的实力对象 （通过static{}）
 *  * 3. 提供一个方法返回单利对象
 *  */


public class Singleton02 {
    public static final Singleton02 INTANCE;

    static {
        INTANCE = new Singleton02();
    }

    public static Singleton02 getInstance() {
        return INTANCE;
    }
}

class Test02 {
    public static void main(String[] args) {
        Singleton02 s1 = Singleton02.getInstance();
        Singleton02 s2 = Singleton02.getInstance();
        System.out.println(s1 == s2);
    }
}
