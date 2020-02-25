package leetcode.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * target: use atomicInteger to achieve atomic operation
 */

public class AtomicInteger01 {
    public static void main(String[] args) {
        //new a 线程任务对象;
        Runnable target = new ThreadTarget01();
        for (int i = 1; i <= 100; ++i) {
            new Thread(target, "第" + i + "个线程").start();
        }
    }
}

class ThreadTarget01 implements Runnable {
    private AtomicInteger atomicInteger = new AtomicInteger();
    @Override
    public void run() {
        for (int i = 1; i <= 10000; ++i) {
            System.out.println(Thread.currentThread().getName() + " count=====>>> " + atomicInteger.incrementAndGet());
        }
    }
}
