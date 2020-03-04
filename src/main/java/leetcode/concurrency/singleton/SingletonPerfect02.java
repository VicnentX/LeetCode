package leetcode.concurrency.singleton;

public class SingletonPerfect02 {
    private SingletonPerfect02() {

    }

    private static class Inner {
        private static final SingletonPerfect02 INSTANCE = new SingletonPerfect02();
    }

    public static SingletonPerfect02 getInstance() {
        return Inner.INSTANCE;
    }
}
