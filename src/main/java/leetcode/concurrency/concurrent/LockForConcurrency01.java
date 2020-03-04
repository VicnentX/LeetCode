package leetcode.concurrency.concurrent;

/**
 * 目标： 加锁机制保证原子性 这个时候加不加volatile其实没所谓
 *
 *
 */

public class LockForConcurrency01 {
    public static void main(String[] args) {
        //create thread instance
        Runnable target = new ThreadTarget02();
        //start 100 thread
        for (int i = 0; i < 100; ++i) {
            new Thread(target, "第 " + i + "个线程").start();
        }
    }
}

class ThreadTarget02 implements Runnable {
    private int count = 0;
    @Override
    //在这个run上面加 synchronized 可以保证原子性
    //也可以在for循环外面 加上对threadtarget02.class的锁
    //我也可以new一个object当作锁
    public void run() {
        synchronized (ThreadTarget02.class) {
            for (int i = 0; i < 10000; ++i) {
                count++;
                System.out.println( Thread.currentThread().getName() + " count =====> " + count);
            }
        }
    }
}

