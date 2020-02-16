package leetcode.concurrency;


/**
 * 目标： 解决多线程下并发修改变量不可见性问题
 *
 * 解决：
 *      1。加锁
 *      2。对共享的变量使用volatile keyword
 */


public class VisibilityDemo02 {
    //main 方法作为主线程
    public static void main (String[] args) {
        //开启一个子线程
        MyThread02 t2 = new MyThread02();
        t2.start();

        //主线程执行

        /**
         * 当然之前有一部分的synchronized 并没有答应 因为那个时候flag还没有被t2线程修改成true
         */
        boolean isfirstprint2 = false;
        boolean isfirstprint22 = false;
        for (int i = 0; i < 1000000000; ++i) {
            //
            synchronized (t2) {
                if (t2.isFlag()) {
                    if (isfirstprint2 == false) {
                        System.out.println("第一次打印t2的flag" + i);
                        isfirstprint2 = true;
                    }
                    System.out.println("在第 " + i + " 次主线程进入循环执行");
                }
            }
        }
    }
}

class MyThread02 extends Thread {
    //
    private boolean flag = false;
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 触发修改flag
        flag = true;
        System.out.println("flag=" + flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}




