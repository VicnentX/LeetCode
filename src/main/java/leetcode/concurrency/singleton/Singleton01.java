package leetcode.concurrency.singleton;

/**
 * target: 饿汉式 （静态常量）
 *
 * steps：
 * 1. 构造器私有
 * 2。 定义一个静态常量保存一个唯一的实力对象 （单利）
 * 3. 提供一个方法返回单利对象
 */

public class Singleton01 {
    //2。 定义一个静态常量保存一个唯一的实力对象 （单利）
    public static final Singleton01 INTANCE = new Singleton01();

    //1. 构造器私有
    private Singleton01(){

    }
    //3. 提供一个方法返回单利对象
    public static Singleton01 getInstance() {
        return INTANCE;
    }
}

class Test01 {
    public static void main(String[] args) {
        Singleton01 s1 = Singleton01.getInstance();
        Singleton01 s2 = Singleton01.getInstance();
        System.out.println(s1 == s2);
    }
}
