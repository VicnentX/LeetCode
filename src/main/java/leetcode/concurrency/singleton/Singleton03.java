package leetcode.concurrency.singleton;

/**
 * /**
 *  * target: 懒汉式 （用的时候才初始化） + synchronized防止线程不安全
 *      缺点 ： 造成阻塞 效率第
 *  *
 *  * steps：
 *  * 1. 构造器私有
 *  * 2。 定义一个静态常量保存一个唯一的实力对象 但是不初始化
 *  * 3. 提供一个方法返回对象（这个对象为null就初始化 不为就return）
 *  */


public class Singleton03 {
    private static Singleton03 INSTANCE;

    private Singleton03() {

    }

//    public synchronized  static Singleton03 getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new Singleton03();
//        }
//        return INSTANCE;
//    }

    //这种方式可以提高性能 不用阻塞在getInstance,进来之后当INSTANCE不空就直接return了
    //！！！！但是线程还是不安全
    //只有上面那种安全
    public static Singleton03 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singleton03.class) {
                INSTANCE = new Singleton03();
            }
        }
        return INSTANCE;
    }
}
