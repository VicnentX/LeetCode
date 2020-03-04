package leetcode.concurrency.singleton;

public class SingletonPerfect01 {
    private static volatile SingletonPerfect01 INSTANCE;

    private SingletonPerfect01() {

    }

    public static SingletonPerfect01 getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonPerfect01.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SingletonPerfect01();
                }
            }
        }
        return INSTANCE;
    }
}
