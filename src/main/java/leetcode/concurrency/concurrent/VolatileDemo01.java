package leetcode.concurrency.concurrent;

/**
 * 目标： 研究volatile的原子性操作
 * 基本观点：volatile!!! 不能 !!! 保证原子性，只能保证可见行
 * 案例：
 *      定义一个共享变量
 *      开启100个线程，每个线程负责累加一万次
 *      在线程结束，看变量结果
 *
 */

public class VolatileDemo01 {
    public static void main(String[] args) {
        //create thread instance
        Runnable target = new ThreadTarget();
        //start 100 thread
        for (int i = 0; i < 100; ++i) {
            new Thread(target, "第 " + i + " 个线程").start();
        }
    }
}

class ThreadTarget implements Runnable {
    private volatile int count = 0;
    @Override
    //在这个run上面加 synchronized 可以保证原子性
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            count++;
            System.out.println( Thread.currentThread().getName() + " count =====> " + count);
        }
    }
}
