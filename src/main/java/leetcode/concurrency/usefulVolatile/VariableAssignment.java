package leetcode.concurrency.usefulVolatile;

import java.util.concurrent.atomic.AtomicInteger;

public class VariableAssignment implements Runnable {
    private volatile boolean flag = false;
    private AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            setDone();
            atomicInteger.incrementAndGet();
        }
    }

    private void setDone() {
//        flag = true;    //这种纯赋值的操作没问题
        flag = !flag;    //这种++ 或者取反是有问题的 因为不保证原子行
    }

    public boolean getFlag() {
        return flag;
    }

    public int getAtomicInteger() {
        return atomicInteger.get();
    }
}

class Test {
    public static void main(String[] args) throws InterruptedException {
        VariableAssignment vr = new VariableAssignment();
        Thread t1 = new Thread(vr);
        Thread t2 = new Thread(vr);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(vr.getFlag());
        System.out.println(vr.getAtomicInteger());
    }
}
