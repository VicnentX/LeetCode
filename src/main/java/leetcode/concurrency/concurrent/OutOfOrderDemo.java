package leetcode.concurrency.concurrent;

/**
 * target: issued brought by the out or order
 * 我的某一次测试 这在211200次是出现了 i = 0 j = 0,因为两个thread里面的顺序都颠倒了
 */

public class OutOfOrderDemo {
    public static int a = 0, b = 0;
    public static int i = 0, j = 0;

    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        while (true) {
            count++;
            a = 0;
            b = 0;
            i = 0;
            j = 0;
            //thread1
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    a = 1;
                    i = b;
                }
            });
            //thread2
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    b = 1;
                    j = a;
                }
            });

            //get the final value of variable after all the thread are done
            t1.start();
            t2.start();
            t1.join();  //让t1线程优先执行完毕
            t2.join();

            System.out.println("count: " + count +" i = " + i + ", j = " + j);
            if (i == 0 && j == 0) break;
        }
    }
}
