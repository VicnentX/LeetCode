package leetcode.concurrency.concurrent;

/**
 * target: volatile 写读看
 *
 * b , a 可能的情况：
 * 3 3
 * 2 1
 * 2 3
 *
 *
 * 3 1 这种情况是可能的 就是b读取的是新的 a读取的是老的
 *
 * 我的方法： 只在b上面加volatile
 * 按照线程2读取了b 那么线程1写入b之前的所有操作都对线程2可见，就是说之前的所有操作的值当我线程2读取的时候都是用的最新值
 */

public class VisibilityHappensBefore {
    private int a = 1;
    private volatile int b = 2;
    public void write() {
        a = 3;
        b = a;
    }

    public void read() {
        System.out.println("b = " + b + ", a = " + a);

        if (b != 3) {
            System.out.println("---------------");
        }
    }

    public static void main(String[] args) {
        while (true) {
            VisibilityHappensBefore visibilityHappensBefore = new VisibilityHappensBefore();
            //offer two thread
            new Thread(new Runnable() {
                @Override
                public void run() {
                    visibilityHappensBefore.write();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    visibilityHappensBefore.read();
                }
            }).start();
        }
    }
}
