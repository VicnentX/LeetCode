package leetcode.concurrency.concurrent;


/**
 * 目标：研究多线程下变量访问的不可见性
 *
 * 准备内容：
 *      准备两个线程
 *      定义一个成员变量
 *      开启两个线程 一个负责修改 一个负责读取
 */


public class VisibilityDemo03 {
    //main 方法作为主线程
    public static void main (String[] args) {
        //开启一个子线程
        MyThread03 t = new MyThread03();
        t.start();

        //主线程执行

        /**
         * volatile
         */
        while (true) {
            if (t.isFlag()) {
                System.out.println("主线程进入循环执行");
            }
        }
    }
}

class MyThread03 extends Thread {
    //
    private volatile boolean flag = false;
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



