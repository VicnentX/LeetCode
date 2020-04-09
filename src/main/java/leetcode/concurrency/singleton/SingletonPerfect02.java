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

    public static void main(String[] args) {
        SingletonPerfect02 s1 = SingletonPerfect02.getInstance();
        SingletonPerfect02 s2 = SingletonPerfect02.getInstance();
        System.out.println(s1 == s2);
    }
}
